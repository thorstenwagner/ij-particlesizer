package de.biomedical_imaging.ij.ndef.particlesizer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.IllegalComponentStateException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ij.IJ;
import ij.gui.WaitForUserDialog;
import ij.plugin.PlugIn;

public class SettingsManager_ extends JDialog implements PlugIn  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SpinnerNumberModel spinnerModelWindowRadius;
	JSpinner spinnerWindowRadius;
	double windowRadius;
	double windowRadiusDefault = 15;
	JCheckBox checkboxWindowRadiusDefault;
	
	SpinnerNumberModel spinnerModelRollingBallRadius;
	JSpinner spinnerRollingBallRadius;
	double rollingBallRadius;
	double rollingBallRadiusDefault=50;
	JCheckBox checkboxRollingBallRadiusDefault;
	
	JSpinner spinnerAMOI;
	JCheckBox checkboxAMOIDefault;
	double amoi;
	double amoiDefault = 16;
	
	JSpinner spinnerMinimalArea ;
	double minimalArea;
	double minimalAreaDefault = 0;
	JCheckBox checkboxMinimalAreaDefault;
	
	JSpinner spinnerMinimalFeretMin;
	double minimalFeretMin;
	double minimalFeretMinDefault = 10;
	JCheckBox checkboxMinimalFeretMin;
	JLabel labelMinimalConvexity;
	JSpinner spinnerMinimalConvexity;
	double minimalConvexity;
	double minimalConvexityDefault = 0;
	JCheckBox checkboxMinimalConvexity;
	
	JSpinner spinnerMinimalSolidity;
	double minimalSolidity;
	double minimalSolidityDefault = 0;
	JCheckBox checkboxMinimalSolidity;
	
	JSpinner spinnerSmoothingFactor;
	double smoothingFactor;
	double smoothingFactorMin = 1;
	double smoothingFactorDefault = 1;
	JCheckBox checkboxSmoothingFactor;
	
	JLabel labelEllipseShapeFilter;
	JLabel labelMinimalEllipseLongAxis;
	JSpinner spinnerEllipseLongAxis;
	double minimalEllipseLongAxis;
	double minimalELlipseLongAxisDefault=5;
	JCheckBox checkboxEllipseLongAxis;
	
	JLabel labelMinimalEllipseShortAxis;
	JSpinner spinnerEllipseShortAxis;
	double minimalEllipseShortAxis;
	double minimalELlipseShortAxisDefault=5;
	JCheckBox checkboxEllipseShortAxis;
	
	JLabel labelMaximalEllipseAspectRatio;
	JSpinner spinnerEllipseAspectRatio;
	double maximalEllipseAspectRatio;
	double maximalELlipseAspectRatioDefault=100;
	JCheckBox checkboxEllipseAspectRatio;
	
	SpinnerNumberModel spinnerModelAMOI;
	SpinnerNumberModel spinnerModelMinimalArea ;
	SpinnerNumberModel spinnerModelMinimalFeretMin;
	SpinnerNumberModel spinnerModelMinimalConvexity;
	SpinnerNumberModel spinnerModelMinimalSolidity;
	SpinnerNumberModel spinnerModelIrregularWatershedConvexityThreshold;
	SpinnerNumberModel spinnerModelEllipseLongAxis;
	SpinnerNumberModel spinnerModelEllipseShortAxis;
	SpinnerNumberModel spinnerModelEllipseAspectRatio;
	
	JCheckBox showBinaryResult;
	JCheckBox doSelectRegion;
	
	JCheckBox doIrregularWatershed;
	
	JLabel labelIrregularWatershedConvexityThreshold;
	JSpinner spinnerIrregularWatershedConvexityThreshold;
	double irregularWatershedConvexityThreshold;
	double irregularWatershedConvexityThresholdMin=0.0;
	double irregularWatershedConvexityThresholdMax=1.0;
	double irregularWatershedConvexityThresholdDefault=0.7;
	JCheckBox checkboxIrregularWatershedConvexityThreshold;
	
	JCheckBox useSingleParticleMode;
	JCheckBox useEllipseFittingMode;
	
	JCheckBox checkboxNoPlotting; 
	JCheckBox checkboxNoNLMeans; 
	/*
	 * Preference Strings
	 */
	private static final String PREF_LOCALTHRESHOLDWINDOWSIZE = "ndef.localThresholdWindowSize";
	private static final String PREF_ROLLINGBALLLRADIUS = "ndef.rollingBallRadius";
	private static final String PREF_OBJECTINTENSITYTHRESHOLD = "ndef.objectIntensityThreshold";
	private static final String PREF_MINSIZE = "ndef.minSize";
	private static final String PREF_MINFERETMIN = "ndef.minFeretMin";
	private static final String PREF_MINELLIPSELONGAXIS =  "ndef.minEllipseLongAxis";
	private static final String PREF_MINELLIPSESHORTAXIS = "ndef.minEllipseShortAxis";
	private static final String PREF_MAXELLIPSEASPECTRATIO = "ndef.minEllipseAspectRatio";
	private static final String PREF_CONVEXITY = "ndef.convexity";
	private static final String PREF_FILTERSOLIDITY = "ndef.filterSolidity";
	private static final String PREF_SHOWBINARYRESULT = "ndef.showBinaryResult";
	private static final String PREF_DOSELECTREGION = "ndef.doSelectRegion";
	private static final String PREF_SMOOTHINGFACTOR = "ndef.smoothingFactor";
	private static final String PREF_DOIRREGULARWATERSHED = "ndef.doIrregularWatershed";
	private static final String PREF_CONVEXITYTHRESHOLD = "ndef.ConvexityThreshold";
	private static final String PREF_USESINGLEPARTICLEMODE = "ndef.useSingleParticleMode";
	private static final String PREF_USEELLIPSEFITTINGMODE = "ndef.useEllipseFittingMode";
	private static final String PREF_NOPLOTTING = "ndef.noPlotting";
	private static final String PREF_NODENOISE = "ndef.noDenoise";
	
	public void init(){
		String defaulttest = "Use Default";
		/*
		 * Window Radius
		 */
		spinnerModelWindowRadius = new SpinnerNumberModel();
		spinnerModelWindowRadius.setValue(windowRadiusDefault);
		spinnerWindowRadius = new JSpinner(spinnerModelWindowRadius);
		checkboxWindowRadiusDefault = new JCheckBox(defaulttest, false);
		checkboxWindowRadiusDefault.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerWindowRadius.setEnabled(!checkboxWindowRadiusDefault.isSelected());
			}
		});
		checkboxWindowRadiusDefault.setToolTipText("Default value: ~1.5% of the image width");
		spinnerModelWindowRadius.setMinimum(1.0);
	
		 
		/*
		 * Rolling ball radius
		 */
		spinnerModelRollingBallRadius = new SpinnerNumberModel();
		spinnerModelRollingBallRadius.setValue(rollingBallRadiusDefault);
		spinnerModelRollingBallRadius.setMinimum(1.0);
		spinnerRollingBallRadius = new JSpinner(spinnerModelRollingBallRadius);
		checkboxRollingBallRadiusDefault = new JCheckBox(defaulttest,false);
		checkboxRollingBallRadiusDefault.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerRollingBallRadius.setEnabled(!checkboxRollingBallRadiusDefault.isSelected());
				
			}
		});
		checkboxRollingBallRadiusDefault.setToolTipText("Default value: 15% of the image width");
		
		/*
		 * Averaged minimal object intensity
		 */
		spinnerModelAMOI = new SpinnerNumberModel();
		spinnerModelAMOI.setValue(amoiDefault);
		spinnerModelAMOI.setMinimum(1.0);
		spinnerAMOI = new JSpinner(spinnerModelAMOI);
		checkboxAMOIDefault = new JCheckBox(defaulttest, false);
		checkboxAMOIDefault.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerAMOI.setValue(amoiDefault);
				spinnerAMOI.setEnabled(!checkboxAMOIDefault.isSelected());
				
			}
		});
		checkboxAMOIDefault.setToolTipText("Default value: " + amoiDefault);
		
		/*
		 * Irregular wathershed
		 */
		doIrregularWatershed = new JCheckBox("");
		doIrregularWatershed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerIrregularWatershedConvexityThreshold.setEnabled(doIrregularWatershed.isSelected());
				checkboxIrregularWatershedConvexityThreshold.setEnabled(doIrregularWatershed.isSelected());
				labelIrregularWatershedConvexityThreshold.setEnabled(doIrregularWatershed.isSelected());
				
			}
		});
		
		spinnerModelIrregularWatershedConvexityThreshold = new SpinnerNumberModel();
		spinnerModelIrregularWatershedConvexityThreshold.setValue(irregularWatershedConvexityThresholdDefault);
		spinnerModelIrregularWatershedConvexityThreshold.setMinimum(irregularWatershedConvexityThresholdMin);
		spinnerModelIrregularWatershedConvexityThreshold.setMaximum(irregularWatershedConvexityThresholdMax);
		spinnerModelIrregularWatershedConvexityThreshold.setStepSize(0.01);
		spinnerIrregularWatershedConvexityThreshold = new JSpinner(spinnerModelIrregularWatershedConvexityThreshold);
		checkboxIrregularWatershedConvexityThreshold = new JCheckBox(defaulttest,false);
		checkboxIrregularWatershedConvexityThreshold.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerIrregularWatershedConvexityThreshold.setValue(irregularWatershedConvexityThresholdDefault);
				spinnerIrregularWatershedConvexityThreshold.setEnabled(!checkboxIrregularWatershedConvexityThreshold.isSelected());
				
			}
		});
		
		/*
		 * Single particle mode
		 */
		
		useSingleParticleMode = new JCheckBox("");
		useSingleParticleMode.setToolTipText("No agglomerates will be splitted. Single particles will be detect by high convexity.");
		useSingleParticleMode.setSelected(false);
		useSingleParticleMode.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(useSingleParticleMode.isSelected()){
					checkboxMinimalConvexity.setSelected(false);
					spinnerMinimalConvexity.setValue(0.85);
					//checkboxMinimalConvexity.setEnabled(false);
					spinnerMinimalConvexity.setEnabled(true);
					//labelMinimalConvexity.setEnabled(false);
				}
				else{
					//labelMinimalConvexity.setEnabled(true);
					//spinnerMinimalConvexity.setEnabled(true);
					checkboxMinimalConvexity.setEnabled(true);
					spinnerMinimalConvexity.setValue(minimalConvexityDefault);
					checkboxMinimalConvexity.setSelected(true);
					spinnerMinimalConvexity.setEnabled(false);					
				}
				
				
			}
		});
		
		/*
		 * Ellipse Fitting Mode
		 */
		useEllipseFittingMode = new JCheckBox("");
		useEllipseFittingMode.setToolTipText("For each detected object an ellipse will be fitted. Strongly overlapping ellipses will be merged.");
		useEllipseFittingMode.setSelected(false);
		useEllipseFittingMode.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				checkboxEllipseShortAxis.setEnabled(useEllipseFittingMode.isSelected());
				checkboxEllipseAspectRatio.setEnabled(useEllipseFittingMode.isSelected());
				checkboxEllipseLongAxis.setEnabled(useEllipseFittingMode.isSelected());
				
				spinnerEllipseAspectRatio.setEnabled(useEllipseFittingMode.isSelected() && !checkboxEllipseAspectRatio.isSelected());
				spinnerEllipseLongAxis.setEnabled(useEllipseFittingMode.isSelected() && !checkboxEllipseLongAxis.isSelected());
				spinnerEllipseShortAxis.setEnabled(useEllipseFittingMode.isSelected() && !checkboxEllipseShortAxis.isSelected());
				
				labelEllipseShapeFilter.setEnabled(useEllipseFittingMode.isSelected());
				labelMinimalEllipseLongAxis.setEnabled(useEllipseFittingMode.isSelected());
				labelMinimalEllipseShortAxis.setEnabled(useEllipseFittingMode.isSelected());
				labelMaximalEllipseAspectRatio.setEnabled(useEllipseFittingMode.isSelected());
				
			}
		});
		labelEllipseShapeFilter = new JLabel("Ellipse shape constraints");
		
		/*
		 * Minimal Ellipse Long Axis
		 */
		
		labelMinimalEllipseLongAxis = new JLabel("Minimal long axis length [px]");
		spinnerModelEllipseLongAxis = new SpinnerNumberModel();
		spinnerModelEllipseLongAxis.setValue(minimalELlipseLongAxisDefault);
		spinnerModelEllipseLongAxis.setStepSize(1);
		spinnerEllipseLongAxis = new JSpinner(spinnerModelEllipseLongAxis);
		checkboxEllipseLongAxis = new JCheckBox(defaulttest, false);
		checkboxEllipseLongAxis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerEllipseLongAxis.setValue(minimalELlipseLongAxisDefault);
				spinnerEllipseLongAxis.setEnabled(!checkboxEllipseLongAxis.isSelected());
				
			}
		});
		
		/*
		 * Minimal Ellipse Short Axis
		 */
		labelMinimalEllipseShortAxis = new JLabel("Minimal short axis length [px]");
		spinnerModelEllipseShortAxis = new SpinnerNumberModel();
		spinnerModelEllipseShortAxis.setValue(minimalELlipseShortAxisDefault);
		spinnerModelEllipseShortAxis.setStepSize(1);
		spinnerEllipseShortAxis = new JSpinner(spinnerModelEllipseShortAxis);
		checkboxEllipseShortAxis = new JCheckBox(defaulttest,true);
		checkboxEllipseShortAxis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerEllipseShortAxis.setValue(minimalELlipseShortAxisDefault);
				spinnerEllipseShortAxis.setEnabled(!checkboxEllipseShortAxis.isSelected());
			}
		});
		
		/*
		 * Maximal Ellipse Aspect Ratio
		 */
		labelMaximalEllipseAspectRatio= new JLabel("Maximal aspect ratio");
		spinnerModelEllipseAspectRatio = new SpinnerNumberModel();
		spinnerModelEllipseAspectRatio.setValue(maximalELlipseAspectRatioDefault);
		spinnerModelEllipseAspectRatio.setStepSize(0.5);
		spinnerEllipseAspectRatio = new JSpinner(spinnerModelEllipseAspectRatio);
		checkboxEllipseAspectRatio = new JCheckBox(defaulttest,true);
		checkboxEllipseAspectRatio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerEllipseAspectRatio.setValue(maximalELlipseAspectRatioDefault);
				spinnerEllipseAspectRatio.setEnabled(!checkboxEllipseAspectRatio.isSelected());
				
			}
		});
		
		/*
		 * Minimal Area
		 */
		spinnerModelMinimalArea = new SpinnerNumberModel();
		spinnerModelMinimalArea.setValue(minimalAreaDefault);
		spinnerModelMinimalArea.setMinimum(0.0);
		spinnerMinimalArea = new JSpinner(spinnerModelMinimalArea);
		checkboxMinimalAreaDefault = new JCheckBox(defaulttest, true);
		checkboxMinimalAreaDefault.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerMinimalArea.setValue(minimalAreaDefault);
				spinnerMinimalArea.setEnabled(!checkboxMinimalAreaDefault.isSelected());
				
			}
		});
		checkboxMinimalAreaDefault.setToolTipText("Default value: " + minimalAreaDefault);
		//Feret Min
		spinnerModelMinimalFeretMin = new SpinnerNumberModel();
		spinnerModelMinimalFeretMin.setValue(minimalFeretMinDefault);
		spinnerModelMinimalFeretMin.setMinimum(0.0);
		spinnerMinimalFeretMin = new JSpinner(spinnerModelMinimalFeretMin);
		checkboxMinimalFeretMin = new JCheckBox(defaulttest, false);
		checkboxMinimalFeretMin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerMinimalFeretMin.setValue(minimalFeretMinDefault);
				spinnerMinimalFeretMin.setEnabled(!checkboxMinimalFeretMin.isSelected());
				
			}
		});
		checkboxMinimalFeretMin.setToolTipText("Default value: " + minimalFeretMinDefault);
		//Convexity
		labelMinimalConvexity= new JLabel("Minimal convexity");
		spinnerModelMinimalConvexity= new SpinnerNumberModel();
		spinnerModelMinimalConvexity.setValue(minimalConvexityDefault);
		
		spinnerModelMinimalConvexity.setMinimum(0.0);
		spinnerModelMinimalConvexity.setMaximum(1.0);
		spinnerModelMinimalConvexity.setStepSize(0.05);
		spinnerMinimalConvexity= new JSpinner(spinnerModelMinimalConvexity);
		checkboxMinimalConvexity= new JCheckBox(defaulttest, false);
		checkboxMinimalConvexity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerMinimalConvexity.setValue(minimalConvexityDefault);
				spinnerMinimalConvexity.setEnabled(!checkboxMinimalConvexity.isSelected());
				
			}
		});
		checkboxMinimalConvexity.setToolTipText("Default value: " + minimalConvexityDefault);
		
		//Solidity

		spinnerModelMinimalSolidity = new SpinnerNumberModel();
		spinnerModelMinimalSolidity.setValue(minimalSolidityDefault);
		spinnerModelMinimalSolidity.setMinimum(0.0);
		spinnerModelMinimalSolidity.setMaximum(1.0);
		spinnerModelMinimalSolidity.setStepSize(0.05);
		spinnerMinimalSolidity= new JSpinner(spinnerModelMinimalSolidity);
		checkboxMinimalSolidity = new JCheckBox(defaulttest, false);
		checkboxMinimalSolidity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerMinimalSolidity.setValue(minimalSolidityDefault);
				spinnerMinimalSolidity.setEnabled(!checkboxMinimalSolidity.isSelected());
				
			}
		});
		checkboxMinimalSolidity.setToolTipText("Default value: " + minimalSolidity);

		
		//Show binary result
		showBinaryResult = new JCheckBox("");
		
		//Do select region
		doSelectRegion = new JCheckBox("");
		
		//No plotting
		checkboxNoPlotting = new JCheckBox("");
		checkboxNoPlotting.setSelected(false);
		
		//No NL Means
		checkboxNoNLMeans = new JCheckBox("");
		checkboxNoNLMeans.setSelected(false);
		
		SpinnerNumberModel spinnerSmoothingFactorModel  = new SpinnerNumberModel();
		spinnerSmoothingFactorModel.setMinimum(1.0);
		spinnerSmoothingFactorModel.setMaximum(20.0);
		spinnerSmoothingFactorModel.setStepSize(1);
		spinnerSmoothingFactorModel.setValue(smoothingFactorDefault);
		spinnerSmoothingFactor = new JSpinner(spinnerSmoothingFactorModel);		
		checkboxSmoothingFactor = new JCheckBox(defaulttest,false);
		checkboxSmoothingFactor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spinnerSmoothingFactor.setValue(smoothingFactorDefault);
				spinnerSmoothingFactor.setEnabled(!checkboxSmoothingFactor.isSelected());
				
			}
		});
	}
	
	public SettingsManager_() {
		super((JDialog)null, "Settings Manager", true);
	}
	
	@Override
	public void run(String arg) {
		init();
		readOldValues();
		this.setTitle("Settings Manager");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		int gridy = 0;
		JPanel pane = (JPanel) this.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Segmentation Headline
		JLabel labelSegmentationHeadline = new JLabel("Segmentation");
		c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		// c.gridwidth=GridBagConstraints.REMAINDER;
		pane.add(labelSegmentationHeadline, c);
		gridy++;

		// Segmentation Seperator
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.ipady = 10;
		c.ipadx = 10;
		c.gridwidth = 3;
		// c.gridwidth=GridBagConstraints.REMAINDER;
		pane.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		gridy++;

		// Circular Window Radius
		JLabel labelWindowRadius = new JLabel("Circular window radius [px]");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelWindowRadius, c);

		
		
		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerWindowRadius, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		
		pane.add(checkboxWindowRadiusDefault, c);
		gridy++;

		// Rolling Ball Radius
		JLabel labelRollingBallRadius = new JLabel("Rolling ball radius [px]");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelRollingBallRadius, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerRollingBallRadius, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		pane.add(checkboxRollingBallRadiusDefault, c);
		gridy++;

		// Averaged Minimal Object Insity (AMOI)
		JLabel labelAMOI = new JLabel("Min. OTB intensity difference (8 bit)");
		labelAMOI.setToolTipText("Minimum object to background (OTB) intensity difference");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelAMOI, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		spinnerAMOI.setToolTipText("Minimum object to background (OTB) intensity difference");
		pane.add(spinnerAMOI, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		pane.add(checkboxAMOIDefault, c);

		gridy++;
		
		//Irregular watershed
		JLabel labelDoIrregularWathershed= new JLabel("Use watershed for irregular structures");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelDoIrregularWathershed, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(doIrregularWatershed, c);
		gridy++;
		
	    labelIrregularWatershedConvexityThreshold = new JLabel("Irregular watershed convexity threshold");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelIrregularWatershedConvexityThreshold, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerIrregularWatershedConvexityThreshold, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		pane.add(checkboxIrregularWatershedConvexityThreshold, c);

		gridy++;

		//Single particle mode
		JLabel labelUseSingleParticleMode = new JLabel("Use single particle mode");
		labelUseSingleParticleMode.setToolTipText("No agglomerates will be splitted. Single particles will be detect by high convexity.");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelUseSingleParticleMode, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		
		pane.add(useSingleParticleMode, c);
		gridy++;
		
		/*
		 * Ellipse fitting
		 */
		JLabel labelUseEllipseFittingMode = new JLabel("Use ellipse fitting mode");
		labelUseEllipseFittingMode.setToolTipText("");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelUseEllipseFittingMode, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		
		pane.add(useEllipseFittingMode, c);
		gridy++;
		
		
		// ========================================================
		/*
		 *  Shape Constraints Headline
		 */
		JLabel labelShapeHeadline = new JLabel("Shape constraints");
		c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(20, 5, 0, 0);
		// c.gridwidth=GridBagConstraints.REMAINDER;
		pane.add(labelShapeHeadline, c);
		gridy++;

		/*
		 *  Shape Constraints Seperator
		 */
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.ipady = 10;
		c.ipadx = 10;
		c.gridwidth = 3;
		pane.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		gridy++;

		/*
		 *  Minimal Area
		 */
		JLabel labelMinimalArea = new JLabel("Minimal area [px^2]");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelMinimalArea, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerMinimalArea, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		pane.add(checkboxMinimalAreaDefault, c);
		gridy++;
		
		/*
		 *  Minimal Feret Min
		 */
		JLabel labelMinimalFeretMin = new JLabel("Minimal feret min. [px]");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelMinimalFeretMin, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerMinimalFeretMin, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		pane.add(checkboxMinimalFeretMin, c);
		gridy++;
		
		/*
		 * Minimal Convexity
		 */
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelMinimalConvexity, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerMinimalConvexity, c);

		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		pane.add(checkboxMinimalConvexity, c);
		gridy++;
		
		/*
		 * Minimal Solidity
		 */
		
		JLabel labelMinimalSolidity= new JLabel("Minimal solidity");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelMinimalSolidity, c);

		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerMinimalSolidity, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		
		pane.add(checkboxMinimalSolidity, c);
		gridy++;
		
		// ========================================================
		/* 
		 * Ellipse shape constraints
		 */
		
		c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(20, 5, 0, 0);
		pane.add(labelEllipseShapeFilter, c);
		gridy++;
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.ipady = 10;
		c.ipadx = 10;
		c.gridwidth = 3;
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		pane.add(sep, c);
		gridy++;
		
		/*
		 * Minimal Ellipse Long Axis Length
		 */
		
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelMinimalEllipseLongAxis, c);

		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerEllipseLongAxis, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		
		pane.add(checkboxEllipseLongAxis, c);
		gridy++;
		
		/*
		 * Minimal Ellipse Short Axis Length
		 */
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelMinimalEllipseShortAxis, c);

		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerEllipseShortAxis, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		
		pane.add(checkboxEllipseShortAxis, c);
		gridy++;
		
		/*
		 * Maximal Ellipse Aspect Ratio
		 */
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelMaximalEllipseAspectRatio, c);

		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		pane.add(spinnerEllipseAspectRatio, c);

		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		
		pane.add(checkboxEllipseAspectRatio, c);
		gridy++;
		
		
		
		// ========================================================
		// Misc
		JLabel labelMiscHeadline = new JLabel("Misc");
		c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(20, 5, 0, 0);
		// c.gridwidth=GridBagConstraints.REMAINDER;
		pane.add(labelMiscHeadline, c);
		gridy++;
		
		// Misc Seperator
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.ipady = 10;
		c.ipadx = 10;
		c.gridwidth = 3;
		pane.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		gridy++;
		
		//Smoothing Factor
		
		JLabel labelSmoothingFactor = new JLabel("Smoothing factor");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelSmoothingFactor, c);
				
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(spinnerSmoothingFactor, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		
		pane.add(checkboxSmoothingFactor, c);
		gridy++;
		
		//Show binary results
		JLabel labelShowBinaryResult = new JLabel("Show binary result");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelShowBinaryResult, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(showBinaryResult, c);
		gridy++;
		
		//Do selection
		
		JLabel labelDoSelection= new JLabel("Ask me to select a region");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelDoSelection, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(doSelectRegion, c);
		gridy++;
		
		JLabel labelNoPlotting= new JLabel("Do not plot size distributions");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelNoPlotting, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(checkboxNoPlotting, c);
		gridy++;
		
		JLabel labelNoDenoising= new JLabel("Do not apply denoising");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(labelNoDenoising, c);
		
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		c.insets = new Insets(0, 5, 0, 5);
		pane.add(checkboxNoNLMeans, c);
		gridy++;
		
		
		
		// BUttons
		JButton okButton = new JButton("OK");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = gridy;
		c.insets = new Insets(10, 0, 5, 5);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double vi = checkboxWindowRadiusDefault.isSelected()?-1:(Double)spinnerWindowRadius.getValue();
				ij.Prefs.set(PREF_LOCALTHRESHOLDWINDOWSIZE,""+vi);
				
				vi = checkboxRollingBallRadiusDefault.isSelected()?-1:(Double)spinnerRollingBallRadius.getValue();
				ij.Prefs.set(PREF_ROLLINGBALLLRADIUS,""+vi);
				
				vi = checkboxAMOIDefault.isSelected()?-1:(Double)spinnerAMOI.getValue();
				ij.Prefs.set(PREF_OBJECTINTENSITYTHRESHOLD, ""+vi);
				
				vi = checkboxMinimalAreaDefault.isSelected()?-1:(Double)spinnerMinimalArea.getValue();
				ij.Prefs.set(PREF_MINSIZE,""+vi);
				
				vi = checkboxMinimalFeretMin.isSelected()?-1:(Double)spinnerMinimalFeretMin.getValue();
				ij.Prefs.set(PREF_MINFERETMIN, ""+vi);
				
				double vd = checkboxEllipseLongAxis.isSelected()?minimalELlipseLongAxisDefault:(Double)spinnerEllipseLongAxis.getValue();
				ij.Prefs.set(PREF_MINELLIPSELONGAXIS, ""+vd);
				
				vd = checkboxEllipseShortAxis.isSelected()?minimalELlipseShortAxisDefault:(Double)spinnerEllipseShortAxis.getValue();
				ij.Prefs.set(PREF_MINELLIPSESHORTAXIS, ""+vd);
				
				vd = checkboxEllipseAspectRatio.isSelected()?maximalELlipseAspectRatioDefault:(Double)spinnerEllipseAspectRatio.getValue();
				ij.Prefs.set(PREF_MAXELLIPSEASPECTRATIO, ""+vd);
			
				vd = checkboxMinimalConvexity.isSelected()?-1.0:(Double)spinnerMinimalConvexity.getValue();
				ij.Prefs.set(PREF_CONVEXITY, ""+vd);
				
				vd = checkboxMinimalSolidity.isSelected()?-1.0:(Double)spinnerMinimalSolidity.getValue();
				ij.Prefs.set(PREF_FILTERSOLIDITY, ""+vd);
				
				ij.Prefs.set(PREF_SHOWBINARYRESULT, showBinaryResult.isSelected());
				
				ij.Prefs.set(PREF_DOSELECTREGION, doSelectRegion.isSelected());
				
				ij.Prefs.set(PREF_SMOOTHINGFACTOR, (Double)spinnerSmoothingFactor.getValue());
				
				ij.Prefs.set(PREF_DOIRREGULARWATERSHED, doIrregularWatershed.isSelected());
				
				ij.Prefs.set(PREF_CONVEXITYTHRESHOLD, (Double)spinnerIrregularWatershedConvexityThreshold.getValue());
				
				ij.Prefs.set(PREF_USESINGLEPARTICLEMODE, useSingleParticleMode.isSelected());
				
				ij.Prefs.set(PREF_USEELLIPSEFITTINGMODE, useEllipseFittingMode.isSelected());
				
				ij.Prefs.set(PREF_NOPLOTTING, checkboxNoPlotting.isSelected());
				
				ij.Prefs.set(PREF_NODENOISE, checkboxNoNLMeans.isSelected());
				
				dispose();
			}
		});
		pane.add(okButton,c);
		
		JButton cancelButton = new JButton("Cancel");
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = gridy;
		c.insets = new Insets(10, 0, 5, 5);
		pane.add(cancelButton,c);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	
		// Display the window.
		try{
			this.setLocation(IJ.getInstance().getLocationOnScreen().x, IJ.getInstance().getLocationOnScreen().y);
		}
		catch(IllegalComponentStateException e){
			//do nothing
		}
		
	    
		this.pack();
		this.setVisible(true);
		
		

	}
	/**
	 * Restores old values
	 */
	private void readOldValues(){
		/*
		 * Window Radius
		 */
		windowRadius=Double.parseDouble(ij.Prefs.get(PREF_LOCALTHRESHOLDWINDOWSIZE, "-1"));
		boolean isDefault = Math.abs(windowRadius+1)<0.0000001;
		checkboxWindowRadiusDefault.setSelected(isDefault);
	
		spinnerWindowRadius.setEnabled(!(isDefault));
		spinnerWindowRadius.setValue(isDefault?windowRadiusDefault:windowRadius);
		
		/*
		 * Rolling Ball Radius
		 */
		rollingBallRadius=Double.parseDouble(ij.Prefs.get(PREF_ROLLINGBALLLRADIUS, "-1"));
		isDefault = Math.abs(rollingBallRadius+1)<0.0000001;
		checkboxRollingBallRadiusDefault.setSelected(isDefault);
		spinnerRollingBallRadius.setEnabled(!(isDefault));
		spinnerRollingBallRadius.setValue(isDefault?50:rollingBallRadius);
		
		/*
		 * AMOI
		 */
		amoi = Double.parseDouble(ij.Prefs.get(PREF_OBJECTINTENSITYTHRESHOLD, "-1"));
		isDefault = Math.abs(amoi+1)<0.0000001;
	
		checkboxAMOIDefault.setSelected(isDefault);
		spinnerAMOI.setEnabled(!(isDefault));
		spinnerAMOI.setValue(isDefault?16.0:amoi);
		
		/*
		 * Irregular Watershed Convexity Threshold
		 */
		irregularWatershedConvexityThreshold = ij.Prefs.get(PREF_CONVEXITYTHRESHOLD, irregularWatershedConvexityThresholdDefault);
		isDefault = irregularWatershedConvexityThreshold == irregularWatershedConvexityThresholdDefault;
		checkboxIrregularWatershedConvexityThreshold.setSelected(isDefault);
		spinnerIrregularWatershedConvexityThreshold.setValue(irregularWatershedConvexityThreshold);
		spinnerIrregularWatershedConvexityThreshold.setEnabled(!isDefault);
		
		/*
		 * Do Irregular Watershed Checkbox
		 */
		boolean doIrrWat = Boolean.parseBoolean(ij.Prefs.get(PREF_DOIRREGULARWATERSHED, "false"));
		doIrregularWatershed.setSelected(doIrrWat); 
		spinnerIrregularWatershedConvexityThreshold.setEnabled(doIrrWat);
		checkboxIrregularWatershedConvexityThreshold.setEnabled(doIrrWat);
		
		/*
		 * Use single particle mode
		 */
		useSingleParticleMode.setSelected(Boolean.parseBoolean(ij.Prefs.get(PREF_USESINGLEPARTICLEMODE, "false")));
		
		/*
		 * Do Ellipse Fitting
		 */
		useEllipseFittingMode.setSelected(Boolean.parseBoolean(ij.Prefs.get(PREF_USEELLIPSEFITTINGMODE, "false")));
		labelEllipseShapeFilter.setEnabled(useEllipseFittingMode.isSelected());
		
		/*
		 * Minimal Area
		 */
		minimalArea=Double.parseDouble(ij.Prefs.get(PREF_MINSIZE, "-1"));
		isDefault= Math.abs(minimalArea+1)<0.0000001;
		checkboxMinimalAreaDefault.setSelected(isDefault);
		spinnerMinimalArea.setEnabled(!(isDefault));
		spinnerMinimalArea.setValue(isDefault?0:minimalArea);
		
		/*
		 * Minimal Feret Min Diameter
		 */
		minimalFeretMin=Double.parseDouble(ij.Prefs.get(PREF_MINFERETMIN, "-1"));
		isDefault = Math.abs(minimalFeretMin+1)<0.0000001;
		checkboxMinimalFeretMin.setSelected(isDefault);
		spinnerMinimalFeretMin.setEnabled(!(isDefault));
		spinnerMinimalFeretMin.setValue(isDefault?10:minimalFeretMin);
		
		/*
		 * Minimal Conexity
		 */
		minimalConvexity = Double.parseDouble(ij.Prefs.get(PREF_CONVEXITY, "-1"));
		isDefault = (Math.abs(minimalConvexity+1)<0.0001);
		checkboxMinimalConvexity.setSelected(isDefault);
		if(useSingleParticleMode.isSelected()){
			spinnerMinimalConvexity.setEnabled(false);
		}else{
			spinnerMinimalConvexity.setEnabled(!(isDefault));
		}
		spinnerMinimalConvexity.setValue(isDefault?minimalConvexityDefault:minimalConvexity);
		
		/*
		 * Minimal Solidity
		 */
		minimalSolidity  = Double.parseDouble(ij.Prefs.get(PREF_FILTERSOLIDITY, "-1"));
		isDefault = (Math.abs(minimalSolidity+1)<0.0001); 
		checkboxMinimalSolidity.setSelected(isDefault);
		spinnerMinimalSolidity.setEnabled(!(isDefault));
		spinnerMinimalSolidity.setValue(isDefault?minimalSolidityDefault:minimalSolidity);
		
		/*
		 * Show binary result
		 */
		
		showBinaryResult.setSelected(Boolean.parseBoolean(ij.Prefs.get(PREF_SHOWBINARYRESULT, "false")));
		
		/*
		 * Do select Region
		 */
		doSelectRegion.setSelected(Boolean.parseBoolean(ij.Prefs.get(PREF_DOSELECTREGION, "true")));
		
		/*
		 * No Plotting checkbox
		 */
		checkboxNoPlotting.setSelected(Boolean.parseBoolean(ij.Prefs.get(PREF_NOPLOTTING, "false")));
		
		/*
		 * No denoise checkbox
		 */
		checkboxNoNLMeans.setSelected(Boolean.parseBoolean(ij.Prefs.get(PREF_NODENOISE, "false")));
		
		
		/*
		 * Smoothing factor
		 */
		
		smoothingFactor = (Double.parseDouble(ij.Prefs.get(PREF_SMOOTHINGFACTOR, "1")));
		
		isDefault = Math.abs(smoothingFactor-smoothingFactorDefault)<0.001;
		checkboxSmoothingFactor.setSelected(isDefault);
		spinnerSmoothingFactor.setEnabled(!isDefault);
		spinnerSmoothingFactor.setValue(smoothingFactor);
		
		/*
		 * Minimum Ellipse long axis
		 */
		labelMinimalEllipseLongAxis.setEnabled(useEllipseFittingMode.isSelected());
		minimalEllipseLongAxis = Double.parseDouble((ij.Prefs.get(PREF_MINELLIPSELONGAXIS, ""+minimalELlipseLongAxisDefault)));
		isDefault = Math.abs(minimalEllipseLongAxis-minimalELlipseLongAxisDefault)<0.001;
		checkboxEllipseLongAxis.setSelected(isDefault);
		checkboxEllipseLongAxis.setEnabled(useEllipseFittingMode.isSelected());
		spinnerEllipseLongAxis.setEnabled(useEllipseFittingMode.isSelected() && !isDefault);
		spinnerEllipseLongAxis.setValue(isDefault?minimalELlipseLongAxisDefault:minimalEllipseLongAxis);
		
		/*
		 * Minimum Ellipse short axis
		 */
		labelMinimalEllipseShortAxis.setEnabled(useEllipseFittingMode.isSelected());
		minimalEllipseShortAxis = Double.parseDouble((ij.Prefs.get(PREF_MINELLIPSESHORTAXIS, ""+minimalELlipseShortAxisDefault)));
		isDefault = Math.abs(minimalEllipseShortAxis-minimalELlipseShortAxisDefault)<0.001;
		checkboxEllipseShortAxis.setSelected(isDefault);
		checkboxEllipseShortAxis.setEnabled(useEllipseFittingMode.isSelected());
		spinnerEllipseShortAxis.setEnabled(useEllipseFittingMode.isSelected() && !isDefault);
		spinnerEllipseShortAxis.setValue(isDefault?minimalELlipseShortAxisDefault:minimalEllipseShortAxis);
		
		/*
		 * Maximum Ellipse aspect ratio
		 */
		labelMaximalEllipseAspectRatio.setEnabled(useEllipseFittingMode.isSelected());
		maximalEllipseAspectRatio = Double.parseDouble((ij.Prefs.get(PREF_MAXELLIPSEASPECTRATIO, ""+maximalELlipseAspectRatioDefault)));
		isDefault = Math.abs(maximalEllipseAspectRatio-maximalELlipseAspectRatioDefault)<0.001;
		checkboxEllipseAspectRatio.setSelected(isDefault);
		checkboxEllipseAspectRatio.setEnabled(useEllipseFittingMode.isSelected());
		spinnerEllipseAspectRatio.setEnabled(useEllipseFittingMode.isSelected() && !isDefault);
		spinnerEllipseAspectRatio.setValue(isDefault?maximalELlipseAspectRatioDefault:maximalEllipseAspectRatio);
		
	}
	
	
}