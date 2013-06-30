(ns hourglass.handler
  (:use compojure.core,
        layouts.application)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (home))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
