(ns leiningen.generate.namespace
  (:refer-clojure :exclude [namespace])
  (:require [leiningen.generate.templates :refer [renderer ->files]]
            [leiningen.new.templates :refer [render-text name-to-path]]
            [leiningen.core.main :as main]))

(def render (renderer "namespace"))

(defn namespace
  "Generate a new namespace."
  [name]
  (let [data {:name name, :sanitized (name-to-path name)}]
    (main/info (render-text "Generating src/{{sanitized}}.clj" data))
    (->files data ["src/{{sanitized}}.clj" (render "source.clj" data)])))
