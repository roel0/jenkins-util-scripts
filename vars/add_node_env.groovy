import hudson.slaves.EnvironmentVariablesNodeProperty

/**
 * Add a permenant node environment variable
 * The node env variable will only be visible
 * after a reallocation of the node (with the node pipeline syntax)
 *
 * @param node_name: The name of the node
 * @param env_key: String containing the env variable name
 * @param env_value: The value of the environment variable
 */
def call(args)
{
    def instance = jenkins.model.Jenkins.instance
    def props
    def node

    if (!(args.containsKey("node_name") &&
          args.containsKey("env_value") &&
          args.containsKey("env_key")))
    {
        throw new IllegalArgumentException()
    }

    node = get_node(args.node_name)
    props = node.nodeProperties.getAll(hudson.slaves.EnvironmentVariablesNodeProperty.class)

    if (props.empty) {
        def entry = new EnvironmentVariablesNodeProperty.Entry(args.env_key, args.env_value)
        def evnp = new EnvironmentVariablesNodeProperty(entry)

        node.nodeProperties.add(evnp)
    } else {
        for (def prop: props) {
            prop.envVars.put(args.env_key, args.env_value)
        }
    }

    instance.save()
}
