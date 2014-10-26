(ns leiningen.new.generators
  (:require [leiningen.new.templates :refer [renderer sanitize year ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "generators"))

(defn generators
  "Create a new generators template for lein-generate."
  [name]
  (let [data {:name          name
              :name-var      "{{name}}"
              :sanitized     (sanitize name)
              :sanitized-var "{{sanitized}}"
              :year          (year)}]
    (main/info "Creating a new generators project called" name)
    (->files data
             [".gitignore"  (render "gitignore" data)]
             ["LICENSE"     (render "LICENSE" data)]
             ["README.md"   (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["src/leiningen/generate/{{sanitized}}.clj"
              (render "generate.clj" data)]
             ["resources/leiningen/generate/{{sanitized}}/source.clj"
              (render "source.clj" data)])))
