(ns leiningen.generate.{{name}}
  (:require [leiningen.generate.templates :refer [renderer ->files]]
            [leiningen.new.templates :refer [name-to-path render-text]]
            [leiningen.core.main :as main]))

(def render (renderer "{{name}}"))

(defn {{name}}
  "FIXME: write documentation"
  [project name]
  (let [data {:name name, :sanitized (name-to-path name)}]
    (main/info (render-text "Creating src/{{sanitized-var}}.clj" data))
    (->files data ["src/{{sanitized-var}}.clj" (render "source.clj" data)])))
