(ns lein-generate.plugin
  (:require [leiningen.generate :as g]
            [leiningen.core.project :as project]))

(defn middleware [project]
  (project/load-plugins project :generators)
  (alter-meta! #'g/generate assoc :subtasks (g/list-generators))
  project)
