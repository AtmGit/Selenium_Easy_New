package com.macys.mst.mcy.datasetup;

import com.macys.mst.artemis.datasetup.DTSDriver;
import com.macys.mst.artemis.datasetup.SelectDataModule;

public class DataSetupDriver extends DTSDriver{
	
	@Override
	public SelectDataModule returndataclass(){
		DataCreateModule dcm = new DataCreateModule();
		return dcm;
	}
	
}
