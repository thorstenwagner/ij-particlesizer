package de.biomedical_imaging.ij.ndef.particlesizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import ij.IJ;
import ij.plugin.PlugIn;


public class ParticleSizer_ implements PlugIn {
	public void run(String arg) {
		try {
			String path = ExportResource("/HAADF.ijm");
			IJ.runMacroFile(path);

		} catch (Exception e) {
			IJ.handleException(e);
		}
	}
	
	 /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     */
    private String ExportResource(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String tmpFolder;
        try {
            stream = this.getClass().getResourceAsStream(resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree
            if(stream == null) {
            	IJ.error("Cannot get resource \"" + resourceName + "\" from Jar file.");
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            File folderDir = new File(IJ.getDirectory("imagej")+"/.particlesizer");

            // if the directory does not exist, create it
            if (!folderDir.exists()) {
            	folderDir.mkdir();
            }
            tmpFolder = folderDir.getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(tmpFolder + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
        	IJ.error(ex.getMessage());
            throw ex;
        } finally {
            stream.close();
            resStreamOut.close();
        }

        return tmpFolder + resourceName;
    }
}
