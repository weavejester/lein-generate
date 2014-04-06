(defproject lein-generate "0.1.0-SNAPSHOT"
  :description "Lein plugin for generating files"
  :url "https://github.com/weavejester/lein-generate"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :profiles
  {:dev {:generators [[lein-generate/generators "0.1.0-SNAPSHOT"]]}})
