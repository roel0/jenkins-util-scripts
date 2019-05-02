/**
 * Run a script on multiple nodes, selected on a given condition
 *
 * <pre>{@code
 *      def cond = { node ->
 *          if (node.labelString == "slave_node") {
 *              return !node.getComputer().countBusy()
 *          }
 *          return false
 *      }
 *
 *      run_on_multiple_nodes("uname -a", cond)
 * }</pre>
 *
 * @param cmd: The script or command you want to run
 * @param condition: Closure deciding on which nodes
 */
def call(args)
{
    if (!(args.containsKey("cmd") &&
          args.containsKey("condition")))
    {
        throw new IllegalArgumentException()
    }

    for (def slave: get_nodes()) {
        if (args.condition(slave)) {
            node(slave.name) {
                if (isUnix()) {
                    sh args.cmd
                } else {
                    bat args.cmd
                }
            }
        }
    }
}
