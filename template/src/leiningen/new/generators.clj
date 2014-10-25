(ns leiningen.new.generators
  (:require [leiningen.new.templates :refer [renderer sanitize year ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "generators"))

(defn generators
  "Create a new generators template for lein-generate."
  [name]
  (let [data {:name        name
              :sanitized   (sanitize name)
              :placeholder "{{sanitized}}"
              :year        (year)}]
    (main/info "Creating a new generators project called" name)
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render "gitignore" data)]
             ["src/leiningen/generate/{{sanitized}}.clj" (render "temp.clj" data)]
             ["resources/leiningen/generate/{{sanitized}}/foo.clj" (render "foo.clj")]
             ["LICENSE" (render "LICENSE" data)])))
