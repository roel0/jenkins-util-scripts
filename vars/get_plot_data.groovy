/**
 * Get the data series from a plot of the current build
 *
 * @param plot: The name of the plot (Part of csvFileName)
 * @param serie: The name of the data series (y-axis label)
 *
 * @return List containing the data
 *         null on failure
 */
def call(args)
{
    def ret = []

    if (!(args.containsKey("plot") && args.containsKey("serie"))) {
        throw new IllegalArgumentException()
    }

    node("master") {
        try {
            def src = currentBuild.rawBuild.getParent().getRootDir().getAbsolutePath()
            def content = readFile "${src}/plot-${args.plot}.csv"

            for (def line: content.split("\n")) {
                if (line.contains(args.serie) && !line.contains("null")) {
                    ret << line.split(',')[0].replace('"', '')
                }
            }

        } catch(java.nio.file.NoSuchFileException e) {
            /*
             * This can have two reasons:
             *
             * 1) Plot doesn't exists yet (first build?)
             * 2) Bad arguments
             */
             ret = null
        }
    }

    return ret
}
