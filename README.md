# lein-generate

A [Leiningen][1] plugin for generating file templates within an existing
project.

[1]: https://github.com/technomancy/leiningen

## Usage

Add `lein-generate` as a plugin dependency to your project or profiles.

```clojure
:plugins [[lein-generate "0.1.0-SNAPSHOT"]]
```

Then add dependencies for the generators you want to use. This plugin
comes with a simple generator that provides a simple namespace
template.

```clojure
:generators [[lein-generator/generators "0.1.0-SNAPSHOT"]]
```

This generator can be used to create a new source file and template:

```bash
lein generate namespace bar.core
```

This creates two new files: `src/bar/core.clj` and
`test/bar/core_test.clj`.

Generators are very similar to Leiningen project templates in both
function and the way they are developed. To create your own library of
generators, start with:

```bash
lein new generators foo
```

Note the plural of "generators". Unlike templates, there can be many
generators in a library.

## License

Copyright © 2014 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
