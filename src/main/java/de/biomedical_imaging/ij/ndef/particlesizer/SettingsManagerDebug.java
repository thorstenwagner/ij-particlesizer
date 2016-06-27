package de.biomedical_imaging.ij.ndef.particlesizer;


import ij.ImageJ;



public class SettingsManagerDebug {

	public static void main(String[] args) {
		ImageJ.main( args );
		SettingsManager_ plugin = new SettingsManager_();
		plugin.run("");

	}

}
