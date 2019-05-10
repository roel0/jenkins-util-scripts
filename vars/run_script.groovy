/**
  * Wrapper around the pipeline sh and bat calls
  * in order to have one single os-independent call.
  * Also added an extra argument, quiet: when true
  * no script output will be printed to stdout
  *
  * @param args: String containing the script path or a Map
  *              containing a sh/bat syntax like mapping
  *
  * @return Based on args, this can be stdout, null, or the script status
  */
def call(args)
{
    def ret

    if (!(args instanceof Map)) {
        args = ["script": args]
    }

    if (isUnix()) {
        if (args.quiet) {
            args.script = "#!/bin/bash \n{\n " + args.script + " \n} &> _output.txt"
        } else {
            args.script = "#!/bin/bash \n" + args.script
        }
        ret = sh(args)
    } else {
        if (args.quiet) {
            args.script = "@echo off\n >_output.txt (\n" + args.script + "\n)"
        }
        ret = bat(args)
        if (ret && args["returnStdout"]) {
            ret = ret.trim()
            if (ret.split("\n").size() > 1) {
                ret = ret.split("\n") - ""
                ret = ret[1..-1].join("\n")
            }
        }
    }

    if (args.returnStdout && args.quiet) {
        ret = readFile "_output.txt"
    }

    return ret
}
