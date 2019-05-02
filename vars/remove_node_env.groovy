/**
 * Delete a permenant node environment variable
 * The node env variable will only be completly removed
 * after a reallocation of the node (with the node pipeline syntax)
 *
 * @param node_name: The name of the node
 * @param env_key: String containing the env variable name
 */
 def call(args)
 {
    def instance = jenkins.model.Jenkins.instance
    def props
    def node

    if (!(args.containsKey("node_name") &&
          args.containsKey("env_key")))
    {
        throw new IllegalArgumentException()
    }

    node = get_node(args.node_name)
    props = node.nodeProperties.getAll(hudson.slaves.EnvironmentVariablesNodeProperty.class)

    for (def prop: props) {
        prop.envVars.remove(args.env_key)
    }

    instance.save()
}
