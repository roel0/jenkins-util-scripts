/**
 * Get a node object by the node name
 *
 * @param name String containing the name of the node
 *
 * @return a node object
 */
def call(name)
{
    def instance = jenkins.model.Jenkins.instance

    if (name != "master") {
        node = instance.getNode(name)
    } else {
        node = instance
    }

    return node
}
