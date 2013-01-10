package org.drools.guvnor.server.jaxrs.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Container for an asset/module pair
 * 
 * @author marco.silva@criticalmanufacturing.com
 *
 */
@XmlRootElement()
public class PackageAsset {
	private Asset asset;
	private Package module;
	
	@XmlElement
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	@XmlElement
	public Package getPackage() {
		return module;
	}
	public void setPackage(Package module) {
		this.module = module;
	}
}
