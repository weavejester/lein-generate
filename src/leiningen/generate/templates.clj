(ns leiningen.generate.templates
  "Functions for handling generator templates that are not in the
  leiningen.new.templates namespace."
  (:require [leiningen.new.templates :refer [sanitize render-text slurp-resource]]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [leiningen.core.main :as main]))

(defn renderer
  "Create a renderer function that looks for mustache templates in the
  right place given the name of your template. If no data is passed, the
  file is simply slurped and the content returned unchanged.

  Functionally the same as leiningen.new.templates/renderer, except
  templates are looked for in leiningen/generate rather than leiningen/new."
  [name]
  (fn [template & [data]]
    (let [path (string/join "/" ["leiningen" "generate" (sanitize name) template])]
      (if-let [resource (io/resource path)]
        (if data
          (render-text (slurp-resource resource) data)
          (io/reader resource))
        (main/abort (format "Template resource '%s' not found." path))))))

(defn ->files
  "Generate a file with content. path can be a java.io.File or string.
  It will be turned into a File regardless. Any parent directories will
  be created automatically."
  [data & paths]
  (doseq [path paths]
    (if (string? path)
      (.mkdirs (io/file (render-text path data)))
      (let [[path content & options] path
            path (io/file (render-text path data))
            options (apply hash-map options)]
        (.mkdirs (.getParentFile path))
        (when (.exists path)
          (main/abort (format "File '%s' already exists." path)))
        (io/copy content (io/file path))
        (when (:executable options)
          (.setExecutable path true))))))
