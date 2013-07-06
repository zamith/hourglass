(ns hourglass.helpers
  (:use [hiccup.page :only [include-css include-js]])
  (:require [clojure.string :as str])
  (:import java.net.URI))

(defn resolve-uri
  [context uri]
  (let [context (if (instance? URI context) context (URI. context))] 
    (.resolve context uri)))

(defn context-uri
  [{:keys [context]} uri]
  (if-let [base (and context (str context "/"))]
    (str (resolve-uri base uri)) uri))

(def pretty-head
  [:head (include-css "//cdn.jsdelivr.net/foundation/4.2.3/css/foundation.min.css")
         (include-css "//cdnjs.cloudflare.com/ajax/libs/normalize/2.1.0/normalize.css")
         [:style {:type "text/css"} "ul { padding-left: 2em }"]])

(defn pretty-body
  [& content]
  [:body {:class "row"}
   (into [:div {:class "columns small-12"}] content)])
