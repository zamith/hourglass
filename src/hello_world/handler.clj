(ns hello-world.handler
  (:use compojure.core,
        hiccup.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn home []
  (html
    [:h1 "Home sweet home"]))

(defroutes app-routes
  (GET "/" [] (home))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
