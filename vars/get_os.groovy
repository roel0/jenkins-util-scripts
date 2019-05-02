/**
 * Get the current operating system
 *
 * @return String containing linux, windows or mac
 */
def call()
{
    if (isUnix()) {
        def uname = sh script: 'uname', returnStdout: true
        if (uname.startsWith("Darwin")) {
            return "mac"
        } else {
            return "linux"
        }
    } else {
        return "windows"
    }
}
