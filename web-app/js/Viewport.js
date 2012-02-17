
/**
 * Window used to create new Entity
 */
isc.Window.create({
			ID : "createNewEntityWindow",
			title : "Modal Window",
			autoSize : true,
			autoCenter : true,
			isModal : true,
			showModalMask : true,
			autoDraw : false,
			items : [isc.DynamicForm.create({
								ID : "createNewEntityForm",
								autoDraw : false,
								height : 200,
								width : 300,
								padding : 4
							}), Button.create({
								width : 150,
								title : "Save",
								click : function() {
									createNewEntityForm.saveData(function(resp) {
												createNewEntityWindow.hide();
											})
								}
							})]
		});

ListGrid.create({
			ID : "artefactsList",
			dataSource : isc.DataSource.create({
						dataFormat : 'json',
						dataURL : 'artefact',
						fields : [{
									name : 'artefact',
									title : 'Artefact'
								}]
					}),
			selectionChanged : function(record) {
				var ds = window[record.artefact + 'DS'];
				entityList.setDataSource(ds);
				entityList.fetchData();
				createNewEntityForm.setDataSource(ds);
			}
		});

isc.HLayout.create({
			width : "100%",
			height : "100%",
			layoutMargin : 20,
			members : [isc.SectionStack.create({
								showResizeBar : true,
								width : 200,
								sections : [{
											title : 'Artefacts',
											canCollapse : false,
											autoShow : true,
											items : [artefactsList]
										}]
							}), isc.SectionStack.create({
								visibilityMode : 'multiple',
								sections : [{
											title : 'Entities',
											autoShow : true,
											canCollapse : false,
											items : [isc.ToolStrip.create({
														height : 24,
														width : '100%',
														members : [Button.create({
																			title : "Add",
																			click : function() {
																				createNewEntityWindow.show();
																				createNewEntityForm.editNewRecord();
																			}
																		}), Button.create({
																			title : "Remove",
																			click : function() {
																				var rec = entityList.getSelectedRecord();
																				if (rec) {
																					entityList.dataSource.removeData(rec);
																				}
																			}
																		})]
													})]
										}, {
											showHeader : false,
											autoShow : true,
											items : [ListGrid.create({
														ID : "entityList",
														canEdit : true,
														canReorderRecords : true,
														dataFetchMode : 'basic',
														alternateRecordStyles : true
													})]
										}]
							})]
		});

artefactsList.fetchData(null, function() {
			artefactsList.selectRecord(0);
		});