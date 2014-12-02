package iCore.VORegistry;

import gr.tns.XMLUtil;
import icore.voregistry.api.AccessRight;
import icore.voregistry.api.BillingCost;
import icore.voregistry.api.GeoLocation;
import icore.voregistry.api.ICTObject;
import icore.voregistry.api.ICTParameter;
import icore.voregistry.api.InputParameter;
import icore.voregistry.api.MetaFeatureSet;
import icore.voregistry.api.NonICTObject;
import icore.voregistry.api.OutputParameter;
import icore.voregistry.api.VOFunction;
import icore.voregistry.api.VOFunctionFeature;
import icore.voregistry.api.VOOwner;
import icore.voregistry.api.VOParameter;
import icore.voregistry.api.VORegistryResponse;
import icore.voregistry.api.VORegistryResponseStatus;
import icore.voregistry.api.VOStatus;
import icore.voregistry.api.VirtualObject;
import icore.voregistry.api.sparql.VOModelVariable;
import icore.voregistry.api.sparql.VORegistryDelete;
import icore.voregistry.api.sparql.VORegistryFilter;
import icore.voregistry.api.sparql.VORegistrySelect;
import icore.voregistry.api.sparql.VORegistryUpdate;
import icore.voregistry.client.AbstractVORegistryClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The Class VORegistryClient_Test.
 */
public class VORegistryClient_Test extends AbstractVORegistryClient {

	/** The vo_reg_uri. */
	private static String vo_reg_uri= "http://83.212.238.247:8010/vo_registry";	
	
	/** The api_key. */
	private static String api_key = "0iL373db9OKnJbSf4X9T6A==";
	
	/** The mode. */
	private static String mode;// "REGISTRATION_FROM_FILE"; //Possible modes:  / "REGISTRATION" / "REGISTRATION_FROM_FILE" / "DISCOVERY" / "UPDATE" / "DELETE" / "SEARCH_BY_FUNCTION_URI"
	
	/** The req. */
	private String req = null; 

	/**
	 * Instantiates a new VO registry client_ test.
	 *
	 * @param _api_key the _api_key
	 */
	public VORegistryClient_Test(String _api_key) {
		super(_api_key);		
	}

	/* (non-Javadoc)
	 * @see icore.voregistry.client.AbstractVORegistryClient#sparqlRequest()
	 */
	@Override
	public String sparqlRequest() {

		if(mode.equals("SEARCH_BY_FUNCTION_URI")){
			try {
				VORegistrySelect vo_registry_query = new VORegistrySelect();
				VORegistryFilter vo_registry_query_filter = new VORegistryFilter();

				vo_registry_query.addQueryVariable(VOModelVariable.VO);
				vo_registry_query.addQueryVariable(VOModelVariable.VOFUNCTION);


				vo_registry_query_filter.voFunctionUri(new URI(""));
				vo_registry_query.addQueryFilter(vo_registry_query_filter.build());

				req = vo_registry_query.build();	

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

		}
		/** 
		 * REGISTRATION 
		 * - The current method shoWcase how we use the JAVA API so as to create a VO to be registered
		 *   (!) Please note that all URIs and links in general are DUMMY. In case you are using specific ontologies you can update them accordingly.
		 *       In addition, please for the registration of new VO, update each time the corresponding URI in the VIrtualObject() constructor method.
		 ***/
		else if(mode.equals("REGISTRATION")){
			VO_Parameters vo_params = new VO_Parameters();
			String vo_base_uri = vo_params.getVo_base_uri();
			System.out.println(vo_params.getVo_base_uri());
			try {
				/** Create Root - the VO **/
				VirtualObject vo = new VirtualObject(new URI(vo_base_uri));
				vo.setType(new URI(vo_params.getVo_type_uri()));
				vo.setDeploymentInfo(new URI(vo_base_uri.concat("/deployment_directory/SmartAssetManagement/DummyVO-001.wsdl")));
				vo.setVOStatus(VOStatus.AVAILABLE);
				vo.setVOMobility(false);
				vo.setVOOwner(new VOOwner(new URI("http://www.iot-icore.eu/users/User#Public"), api_key));

				/** Add ICT **/
				ICTObject ict = new ICTObject();
				/** Add ICT Geolocation **/
				ict.setGeoLocation(new GeoLocation(100.0, 100.0, 0.0));		

				/** Add ICT Parameter **/
				ICTParameter ict_parameter = new ICTParameter();
				ict_parameter.setName("Location");
				ict_parameter.getMetaFeatureSet().add( new MetaFeatureSet(new URI("http://www.iot-icore.eu/ontologies/properties#Position"), "3.3") );					
				ict.getICTParameters().add(ict_parameter);

				/** Add Non-ICT Object **/
				NonICTObject non_ict = new NonICTObject();
				non_ict.setName("Smart Room");
				non_ict.setType(new URI("http://www.iot-icore.eu/ontologies/places/indoor#Room"));
				non_ict.setGeoLocation(new GeoLocation(100.0, 100.0, 0.0));
				ict.getNonICTObjects().add(non_ict);
				vo.setICTObject(ict);

				/** VO Function **/
				VOFunction vo_function = new VOFunction();
				vo_function.setName("Location");
				vo_function.setDescription("Provides the location of the device in x,y,z co-ords");
				/** Input Parameter Names **/
				HashSet<String> inputs = new HashSet<String>();
				inputs.add("Input_X");
				vo_function.setInputNames(inputs);
				/** Output Parameter Names **/
				HashSet<String> outputs = new HashSet<String>();
				outputs.add("Output_X");
				vo_function.setOutputNames(outputs);

				/** Input Parameter **/
				InputParameter input_parameter = new InputParameter();
				input_parameter.setName("Input_X");
				input_parameter.getMetaFeatureSet().add(new MetaFeatureSet(new URI("http://www.iot-icore.eu/ontologies/Datatypes#Input"), "data_type_x"));
				vo_function.getInputParameters().add(input_parameter);

				/** Output Parameter **/
				OutputParameter output_parameter = new OutputParameter();
				output_parameter.setName("Output_X");
				output_parameter.getMetaFeatureSet().add(new MetaFeatureSet(new URI("http://www.iot-icore.eu/ontologies/MeasurementUnitis#Unit"), "unit_x"));
				vo_function.getOutputParameters().add(output_parameter);

				/** VO Function Features **/
				//- Cost
				vo_function.getVOFunctionFeatures().add(new VOFunctionFeature(new URI("http://www.iot-icore.eu/ontologies/features.owl#Cost"), "Energy", 1.0));
				vo_function.getVOFunctionFeatures().add(new VOFunctionFeature(new URI("http://www.iot-icore.eu/ontologies/features.owl#Cost"), "Expenditure", 1.0));
				vo_function.getVOFunctionFeatures().add(new VOFunctionFeature(new URI("http://www.iot-icore.eu/ontologies/features.owl#Cost"), "Network", 1.0));
				//- Utilities
				vo_function.getVOFunctionFeatures().add(new VOFunctionFeature(new URI("http://www.iot-icore.eu/ontologies/features.owl#Utility"), "Quality", 3.0));
				vo_function.getVOFunctionFeatures().add(new VOFunctionFeature(new URI("http://www.iot-icore.eu/ontologies/features.owl#Utility"), "Performance", 3.0));
				vo_function.getVOFunctionFeatures().add(new VOFunctionFeature(new URI("http://www.iot-icore.eu/ontologies/features.owl#Utility"), "Security", 3.0));
				vo.getVOFunctions().add(vo_function);

				/** Convert VO description into XML **/
				req = XMLUtil.toXML(vo);	

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}			
		}		
		/** REGISTRATION FROM XML/JSON FILE **/
		else if(mode.equals("REGISTRATION_FROM_FILE")){
			req = "../vo_registry_sample_interoperable_v2.6.0/src/vo/templates/vo_description_template_CNET.xml";
		}
		/** DISCOVERY **/
		else if(mode.equals("DISCOVERY")){

			VORegistrySelect vo_registry_query = new VORegistrySelect();
			VORegistryFilter vo_registry_query_filter = new VORegistryFilter();

			vo_registry_query.addQueryVariable(VOModelVariable.VO);
			vo_registry_query.addQueryVariable(VOModelVariable.VOTYPE);
			vo_registry_query.addQueryVariable(VOModelVariable.VOSTATUS);
			vo_registry_query.addQueryVariable(VOModelVariable.VOOWNER);

			vo_registry_query.addQueryVariable(VOModelVariable.VOFUNCTION);
			vo_registry_query.addQueryVariable(VOModelVariable.VOFUNCTION_INPUTPARAMETERS);
			vo_registry_query.addQueryVariable(VOModelVariable.VOFUNCTION_OUTPUTPARAMETERS);
			vo_registry_query.addQueryVariable(VOModelVariable.VOFUNCTION_FEATURES);
			vo_registry_query.addQueryVariable(VOModelVariable.VOFUNCTION_ACCESSRIGHTS);

			vo_registry_query.addQueryVariable(VOModelVariable.ICT);
			vo_registry_query.addQueryVariable(VOModelVariable.ICTPARAMETER);
			vo_registry_query.addQueryVariable(VOModelVariable.ICT_GEOLOCATION);

			vo_registry_query.addQueryVariable(VOModelVariable.NONICT);
			vo_registry_query.addQueryVariable(VOModelVariable.NONICT_NAME);
			vo_registry_query.addQueryVariable(VOModelVariable.NONICT_TYPE);
			vo_registry_query.addQueryVariable(VOModelVariable.NONICT_GEOLOCATION);

			vo_registry_query_filter.voStatus(VOStatus.AVAILABLE);
			vo_registry_query.addQueryFilter(vo_registry_query_filter.build());

			req = vo_registry_query.build();
		}

		/** 
		 * UPDATE 
		 *  - In the current example we assume that want to update ICT Geolocation.
		 *    So, we are using the corrsponding vo_uri concatenated with '/ict' as it is defined in the VO Naming Scheme 
		 ***/
		else if(mode.equals("UPDATE")){
			VORegistryUpdate request = new VORegistryUpdate();
			String vo_base_uri = "http://www.iot-icore.eu/vo/DummyVO-001";
			try {
				request.ictGeolocation(new URI(vo_base_uri.concat("/ict")), 500.0, 600.0, 0.0);
				req = request.build();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		/** 
		 * DELETE 
		 * - In the current example we present how we can delete a VO from the VO Registry just by using its vo uri.
		 *   
		 ****/
		else if(mode.equals("DELETE")){
			VORegistryDelete request = new VORegistryDelete();
			String vo_base_uri = "http://www.iot-icore.eu/vo/SmartAssetManagement/DummyVO-001";
			request.vo(vo_base_uri);				
			req = request.build();
		}

		return req;
	}

	/**
	 *  Method that prints out the results *.
	 *
	 * @param _response the _response
	 */
	public static void printOutTheResults(VORegistryResponse _response){

		if(_response!=null){
			HashMap<URI, VirtualObject> _vo_results = _response.getVODiscoveryResults();
			int resultsNUmber = _response.getVODiscoveryResultsNumber();
			int responseStatus = _response.getStatus().getStatusCode();
			int vo_counter = 0; 

			System.out.println(
					responseStatus+": "+_response.getStatus().getStatusMessage() + 
					" | " + resultsNUmber + " results"
					);

			if(_response.getStatus().equals(VORegistryResponseStatus.OK)){
				//-3.1: Get all available VOs that fulfill the discovery request
				/** Get all VOs **/
				Iterator<URI> vo_uri = _vo_results.keySet().iterator();
				VirtualObject vo = null;

				while (vo_uri.hasNext()) {
					/* Increase VO Counter */
					vo_counter++;
					/* Create VO */
					vo = _vo_results.get(vo_uri.next());
					System.out.println("------------------\nRESULT[" + vo_counter + "]\n------------------\n\t"); 
					System.out.println("Virtual Object{");
					System.out.println( 
							"\tVO URI: "+vo.getResourceURI()
							);

					//- Get VO Type
					if(vo.getType()!=null){
						System.out.println( 
								"\tTYPE: " + vo.getType()  
								);
					}

					//- Get VO Deployment Info
					if(vo.getDeploymentInfo()!=null){
						System.out.println( 
								"\tDEPLOYMENT INFO: " + vo.getDeploymentInfo()  
								);
					}

					//- Get VO Status
					if(vo.getVOStatus()!=null){
						System.out.println( 
								"\tVO STATUS: " + vo.getVOStatus()  
								);
					}

					System.out.println( 
							"\tMOBILITY: " + vo.isMobileVO() 
							);

					//- Get VO Owner
					if(vo.getVOOwner()!=null){
						System.out.println("\tVO Owner{");
						System.out.println("\t\tURI: "+
								vo.getVOOwner().getResourceURI()
								);
						System.out.println("\t}");
					}

					/** VO Parameters **/
					if( ! vo.getVOParameters().isEmpty()){
						System.out.println("\tVO Parameters[");
						int vp_c = 1;
						for(VOParameter vo_par : vo.getVOParameters()){
							System.out.println("\t\tVO Parameter "+vp_c+" { ");
							System.out.println(
									"\t\t\tURI: " + vo_par.getResourceURI() + 
									"\n\t\t\tNAME: " + vo_par.getName()
									);		
							/** VO Parameter Meta Features **/
							if( ! vo_par.getMetaFeatureSet().isEmpty() ){
								System.out.println("\t\t\tMeta Features[");
								int c = 1;
								for(MetaFeatureSet mfs : vo_par.getMetaFeatureSet()){		    																	
									System.out.println("\t\t\t\tVO Parameter Meta Feature "+c+" { ");
									System.out.println("\t\t\t\t\t\t\tURI: "+mfs.getResourceURI() +
											"\n\t\t\t\t\t\t\tType: "+mfs.getType() +
											"\n\t\t\t\t\t\t\tValue: "+ mfs.getValue());
									System.out.println("\t\t\t\t}");
									c++;
								}//end for
								System.out.println("\t\t\t]");
							}

							/** VO Parameter Access Rights **/
							if( ! vo_par.getAccessRights().isEmpty() ){
								System.out.println("\t\t\tAccess Rights[");
								int c = 1;
								for(AccessRight ar : vo_par.getAccessRights()){		    																	
									System.out.println("\t\t\t\tAccess Right "+c+" { ");
									System.out.println("\t\t\t\t\tURI: "+ar.getResourceURI() +
											"\n\t\t\t\t\tType: "+ar.getType() +
											"\n\t\t\t\t\tValue: "+ ar.getValue());
									System.out.println("\t\t\t\t}");
									c++;
								}//end for
								System.out.println("\t\t\t]");
							}

							/** VO Parameter Billing Costs **/
							if( ! vo_par.getBillingCosts().isEmpty() ){
								System.out.println("\t\t\tBilling Costs[");
								int c = 1;
								for(BillingCost bc : vo_par.getBillingCosts()){		    																	
									System.out.println("\t\t\t\tBilling Cost "+c+" { ");
									System.out.println("\t\t\t\t\t\t\tURI: " + bc.getResourceURI() +
											"\n\t\t\t\t\t\t\tType: " + bc.getType() +
											"\n\t\t\t\t\t\t\tValue: "+ bc.getValue());
									System.out.println("\t\t\t\t}");
									c++;
								}//end for
								System.out.println("\t\t\t]");
							}
							System.out.println("\t\t}");
							vp_c++;
						}//end for VO Parameters
						System.out.println("\t]");
					}//end of VO Parameters

					/** VO Functions **/
					if(!vo.getVOFunctions().isEmpty()){
						System.out.println("\n\tVO Functions[ ");
						int fun_c = 1;
						for(VOFunction vo_function : vo.getVOFunctions()){
							System.out.println("\t\tVO Function "+fun_c+" { ");
							System.out.println(
									"\t\t\tURI: "+vo_function.getResourceURI() + 
									"\n\t\t\tNAME: " + vo_function.getName() + 
									"\n\t\t\tDESCRIPTION: " + vo_function.getDescription()
									);
							/** VO Function Input names **/
							if(vo_function.getInputNames()!=null){
								System.out.print("\t\t\tINPUT NAME:[ ");
								for(String input_name : vo_function.getInputNames()){
									System.out.print(" "+input_name+" ");
								}
								System.out.println(" ]");
							}

							/** VO Function Output names **/
							if(vo_function.getOutputNames()!=null){
								System.out.print("\t\t\tOUTPUT NAME:[ ");
								for(String output_name : vo_function.getOutputNames()){
									System.out.print(" "+output_name+" ");
								}
								System.out.println(" ]");
							}

							/** VO Function Feature **/
							if(!vo_function.getVOFunctionFeatures().isEmpty()){
								System.out.println("\t\t\tFUNCTION FEATURES:[ ");
								int c = 1;
								for(VOFunctionFeature voff : vo_function.getVOFunctionFeatures()){
									System.out.println("\t\t\t\tVO Function Fature  "+c+" { ");
									System.out.println(
											"\t\t\t\t\tURI: " + voff.getResourceURI() + "\n" + 
													"\t\t\t\t\tType: " + voff.getType() + "\n" + 
													"\t\t\t\t\tName: " + voff.getName() + "\n" + 
													"\t\t\t\t\tValue: " + voff.getValue()
											);
									System.out.println("\t\t\t\t}");
									c++;
								}//end for VO Function features
								System.out.println("\t\t\t]");
							}

							/** VO Function Input Parameters **/
							if(!vo_function.getInputParameters().isEmpty()){
								System.out.println("\t\t\tInput Parameters[");
								int c = 1;
								for(InputParameter input_par : vo_function.getInputParameters()){
									System.out.println("\t\t\t\tInput Parameter "+c+" { ");
									System.out.println(
											"\t\t\t\t\tURI:" + input_par.getResourceURI() + "\n" + 
													"\t\t\t\t\tNAME:" + input_par.getName() 
											);		
									//- Get Input Param Meta Features
									if(!input_par.getMetaFeatureSet().isEmpty()){
										System.out.println("\t\t\t\t\tMeta Features[");
										int fc = 1;
										for(MetaFeatureSet mfs : input_par.getMetaFeatureSet()){		    																	
											System.out.println("\t\t\t\t\t\tMeta Feature "+fc+" { ");
											System.out.println("\t\t\t\t\t\t\tURI: "+mfs.getResourceURI() +
													"\n\t\t\t\t\t\t\tType: "+mfs.getType() +
													"\n\t\t\t\t\t\t\tValue: "+ mfs.getValue());
											System.out.println("\t\t\t\t\t\t}");
											fc++;
										}//end for
										System.out.println("\t\t\t\t\t]");
									}
									System.out.println("\t\t\t\t}");
									c++;
								}//end for Input Parameters
								System.out.println("\t\t\t]");
							}//end if check Input Parameter

							/** VO Function Output Parameters **/
							if(!vo_function.getOutputParameters().isEmpty()){
								System.out.println("\t\t\tOutput Parameters[");
								int c = 1;
								for(OutputParameter output_par : vo_function.getOutputParameters()){
									System.out.println("\t\t\t\tOutput Parameter "+c+" { ");
									System.out.println(
											"\t\t\t\t\tURI:" + output_par.getResourceURI() + "\n" + 
													"\t\t\t\t\tNAME:" + output_par.getName() 
											);		
									//- Get Input Param Meta Features
									if(!output_par.getMetaFeatureSet().isEmpty()){
										System.out.println("\t\t\t\t\tMeta Features[");
										int fc = 1;
										for(MetaFeatureSet mfs : output_par.getMetaFeatureSet()){		    																	
											System.out.println("\t\t\t\t\t\tMeta Feature "+fc+" { ");
											System.out.println("\t\t\t\t\t\t\tURI: "+mfs.getResourceURI() +
													"\n\t\t\t\t\t\t\tType: "+mfs.getType() +
													"\n\t\t\t\t\t\t\tValue: "+ mfs.getValue());
											System.out.println("\t\t\t\t\t\t}");
											fc++;
										}//end for
										System.out.println("\t\t\t\t\t]");
									}
									System.out.println("\t\t\t\t}");
									c++;
								}//end for Output Parameters
								System.out.println("\t\t\t]");
							}//end if check Output Parameter

							/** VO Function Access Rights **/
							if(!vo_function.getAccessRights().isEmpty()){
								System.out.println("\t\t\tACCESS RIGHTS:[ ");
								int c = 1;
								for(AccessRight vo_ar : vo_function.getAccessRights()){
									System.out.println("\t\t\t\tAccess Right "+c+" { ");
									System.out.println(
											"\t\t\t\t\tURI: " + vo_ar.getResourceURI() + "\n" + 
													"\t\t\t\t\tType: " + vo_ar.getType() + "\n" + 
													"\t\t\t\t\tValue: " + vo_ar.getValue()
											);
									System.out.println("\t\t\t\t}");
									c++;
								}//end for VO Access Rights
								System.out.println("\t\t\t]");
							}//access rights

							/** Get VO Function Billing Costs **/
							if(!vo_function.getBillingCosts().isEmpty()){
								System.out.println("\t\t\tBILLING COSTS:[ ");
								int c = 1;
								for(BillingCost vo_bc : vo_function.getBillingCosts()){
									System.out.println("\t\t\t\tBilling Cost "+c+" { ");
									System.out.println(
											"\t\t\t\t\tURI: " + vo_bc.getResourceURI() + "\n" + 
													"\t\t\t\t\tType: " + vo_bc.getType() + "\n" + 
													"\t\t\t\t\tValue: " + vo_bc.getValue()
											);
									System.out.println("\t\t\t\t}");
									c++;
								}//end for VO Billing costs
								System.out.println("\t\t\t]");			    					
							}//end of billing costs
							fun_c++;
						}//end for VO Functions
						System.out.println("\t]\n");
					}//end if check null VO Functions


					/** ICT Object **/
					ICTObject ict = vo.getICTObject();
					if(ict!=null){
						System.out.println("\tICT Object{");
						System.out.println("\t\tURI: "+
								ict.getResourceURI()
								);

						/** ICT Parameters **/
						if(!ict.getICTParameters().isEmpty()){
							System.out.println("\t\tICT Parameters[");
							for(ICTParameter ict_par : ict.getICTParameters()){
								System.out.println("\t\t\tICT Parameter{ ");
								System.out.println(
										"\t\t\t\tURI:"+ict_par.getResourceURI() + "\n" + 
												"\t\t\t\tNAME:"+ict_par.getName() 
										);		
								//- Get ICT Param Meta Features
								if(!ict_par.getMetaFeatureSet().isEmpty()){
									System.out.println("\t\t\t\tICT Parameter Meta Features[");
									int c =1;
									for(MetaFeatureSet mfs : ict_par.getMetaFeatureSet()){		    																	
										System.out.println("\t\t\t\t\tICT Parameter Meta Feature "+c+" { ");
										System.out.println("\t\t\t\t\t\t\tURI: "+mfs.getResourceURI() +
												"\n\t\t\t\t\t\t\tType: "+mfs.getType() +
												"\n\t\t\t\t\t\t\tValue: "+ mfs.getValue());
										System.out.println("\t\t\t\t\t}");
										c++;
									}//end for
									System.out.println("\t\t\t\t]");
								}
							}//end for ICT Parameters
						}//end if check ICT Parameter

						/** ICT GeoLocation **/
						GeoLocation ict_geo = vo.getICTObject().getGeoLocation();
						if(ict_geo!=null){
							System.out.println("\t\tICT GeoLocation{ \n"+
									"\t\t\tLong: "+ict_geo.getLongitude() + "\n" +
									"\t\t\tLat: "+ict_geo.getLatitude() + "\n" +
									"\t\t\tAlt: "+ict_geo.getAltitude() + "\n" +
									"\t\t}");
						}
						/** Non-ICT Object **/
						System.out.println("\n\t\tNonICT Objects[");
						int c = 1;
						for(NonICTObject non_ict_object : vo.getICTObject().getNonICTObjects()){
							if(non_ict_object!=null){
								System.out.println("\t\t\tNon-ICT Object "+c+" { ");
								System.out.println(
										"\t\t\t\tURI: "+non_ict_object.getResourceURI() 
										);
								//- Get Non-ICT Name
								if(non_ict_object.getName() != null){
									System.out.println(
											"\t\t\t\tNAME: " + non_ict_object.getName()
											);
								}
								//- Get Non-ICT Type
								if(non_ict_object.getType() != null){
									System.out.println(
											"\t\t\t\tTYPE: " + non_ict_object.getType()
											);
								}

								//- Get Non-ICT GeoLocation
								GeoLocation non_ict_geo = non_ict_object.getGeoLocation();
								if(non_ict_geo!=null){
									System.out.println("\t\t\t\tNon-ICT GeoLocation{ \n"+
											"\t\t\t\t\tLong: "+non_ict_geo.getLongitude() + "\n" +
											"\t\t\t\t\tLat: "+non_ict_geo.getLatitude() + "\n" +
											"\t\t\t\t\tAlt: "+non_ict_geo.getAltitude() + "\n" +
											"\t\t\t\t}");
								}
								System.out.println("\t\t\t}");
								c++;
							}
						}//end for
						System.out.println("\t\t]\n");		    				
					}//end of ICT

					System.out.println("}");
					System.out.println("-----------------------------------------------\n\n");
				}//end for
			}//end if check VOs' container
		}//end if
	}//end if check null





	/**
	 *  Init *.
	 *
	 * @param mode1 the mode1
	 */
	public void Init(String mode1){

		/**-Step 1: CREATE CLIENT **/
		VORegistryClient_Test voc = new VORegistryClient_Test(api_key);
		VORegistryClient_Test.mode = mode1;

		/**-Step 2: CONNECT **/
		voc.connect(vo_reg_uri);		

		/**-Step 3: SEND THE REQUEST **/
		/**-A: REGISTRATION **/
		if(mode.equals("REGISTRATION")){
			System.out.println("Request Sent.\nWaiting for response....");
			voc.sendRegistrationRequest( voc.sparqlRequest() );
			System.out.println("Response Received.");
			System.out.println("\nResponse status\n-----------------\n"+voc.getResponseStatus().toString());
		}
		else if(mode.equals("REGISTRATION_FROM_FILE")){
			System.out.println("Request Sent.\nWaiting for response....");
			voc.registrationFromXMLRequest( voc.sparqlRequest() );
			System.out.println("Response Received.");
			System.out.println("\nResponse status\n-----------------\n"+voc.getResponseStatus().toString());
		}
		/**-B: DISCOVERY **/
		else if(mode.equals("DISCOVERY")){
			System.out.println("Request Sent.\nWaiting for response....");
			voc.sendDiscoveryRequest( voc.sparqlRequest() );
			System.out.println("Response Received.");
			System.out.println("\nResponse status\n-----------------\n"+voc.getResponseStatus().toString());
			printOutTheResults(voc.getResponse());
		}
		/**-C: UPDATE **/
		else if(mode.equals("UPDATE")){
			System.out.println("Request Sent.\nWaiting for response....");
			voc.sendUpdateRequest( voc.sparqlRequest() );
			System.out.println("Response Received.");
			System.out.println("\nResponse status\n-----------------\n"+voc.getResponseStatus().toString());
		}
		/**-D: DELETE **/
		else if(mode.equals("DELETE")){
			System.out.println("Request Sent.\nWaiting for response....");
			voc.sendDeleteRequest( voc.sparqlRequest() );
			System.out.println("Response Received.");
			System.out.println("\nResponse status\n-----------------\n"+voc.getResponseStatus().toString());
		}		
	}

}