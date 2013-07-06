(ns hourglass.handler
  (:use compojure.core,
        layouts.application)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])
            [clojure.string :as str]
            [hourglass.helpers :as misc]))

(def users {"root" {:username "root"
                    :password (creds/hash-bcrypt "admin_password")
                    :roles #{::admin}}
            "jane" {:username "jane"
                    :password (creds/hash-bcrypt "user_password")
                    :roles #{::user}}})

(defn- create-user
  [{:keys [username password] :as user-data}]
  (assoc {} :username username
         :password (creds/hash-bcrypt password)
         :roles #{::user}))

(defroutes app-routes
  (GET "/" [] (home))
  (GET "/signup" req (signup-form (:flash req)))
  (POST "/signup" {{:keys [username password confirm] :as params} :params :as req}
    (if (and (not-any? str/blank? [username password confirm]) (= password confirm))
      (let [user (create-user (select-keys params [:username :password]))]
        (friend/merge-authentication (resp/redirect "/dashboard") user))
      (assoc (resp/redirect (str (:context req) "/signup")) :flash "password don't match")))
  (GET "/dashboard" req (friend/authenticated (str "Hello, ") (resp/redirect "/signup")))
  (GET "/login" req login-form)
  (GET "/users" req (str users))
  (friend/logout (POST "/logout" request (resp/redirect "/")))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site
    (friend/authenticate
      app-routes
      {:allow-anon? true
       :login-uri "/login"
       :default-landing-uri "/"
       :unauthorized-handler #(-> (str "You do not have sufficient privileges to access " (:uri %))
                                resp/response
                                (resp/status 401))
       :credential-fn #(creds/bcrypt-credential-fn users %)
       :workflows [(workflows/interactive-login-redirect)]})))
