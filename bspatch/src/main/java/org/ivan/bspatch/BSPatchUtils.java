package org.ivan.bspatch;

/**
 *
 * @author ivan at 2016-10-10  11:52
 * @version v1.0
 * @since v1.0
 */

public class BSPatchUtils {

    static {
        System.loadLibrary("bspatch");
    }

    /**
     * A native method that is implemented by the 'bspatch' native library,
     * which is packaged with this application.
     */
    public native static void patch(String oldFile, String newFile, String patch);
}
