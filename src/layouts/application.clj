(ns layouts.application
  (:require [hiccup.page :as h]
            [hourglass.helpers :as misc]))

(defn layout [page]
  (h/html5
    misc/pretty-head
      (misc/pretty-body page)))

(defn home []
  (layout
    [:h1 "Home sweet home"]))

(def login-form
  (layout
    [:div {:class "row"}
      [:div {:class "columns small-12"}
        [:h3 "Login"]
        [:div {:class "row"}
        [:form {:method "POST" :action "login" :class "columns small-4"}
          [:div "Username" [:input {:type "text" :name "username"}]]
          [:div "Password" [:input {:type "password" :name "password"}]]
          [:div [:input {:type "submit" :class "button" :value "Login"}]]]]]]))

(defn signup-form [flash]
  (layout
    [:div {:class "row"}
      [:div {:class "columns small-12"}
        [:h1 "Sign up"]
        [:div {:class "row"}
          [:form {:method "POST" :action "signup" :class "columns small-4"}
            [:div "Username" [:input {:type "text" :name "username" :required "required"}]]
            [:div "Password" [:input {:type "password" :name "password" :required "required"}]]
            [:div "Confirm" [:input {:type "password" :name "confirm" :required "required"}]]
        [:div
          [:input {:type "submit" :class "button" :value "Sign up"}]
          [:span {:style "padding:0 0 0 10px;color:red;"} flash]]]]]]))
