'use strict';
module App.Controllers
{

    export class MesterCtrl
    {
        public static controllerId: string = 'mesterCtrl';
     
     //#region Variables
        controllerId = PapaCtrl.controllerId;
        common: App.Shared.ICommon;
        datacontext: App.Services.IDatacontext;
        dataService: Services.IDataService;
        log: any;   
        
              
        //#endregion
        constructor(common, datacontext, dataService: Services.IDataService)
        {
            this.common = common;
            this.datacontext = datacontext;
            this.log = common.logger.getLogFn();
            
            this.dataService = dataService;
            
            
            // Queue all promises and wait for them to finish before loading the view
            // Queue all promises and wait for them to finish before loading the view
            this.activate([   ]);
        }

        // TODO: is there a more elegant way of activating the controller - base class?
        activate(promises: Array<ng.IPromise<any>>)
        {
            this.common.activateController(promises, this.controllerId)
                .then(() => { this.log('Activated Dashboard View'); });
        }
        
    }

    // register controller with angular
    app.controller(MesterCtrl.controllerId, ['common', 'datacontext', 'dataService',
        (c, dc, dataService) => new App.Controllers.PapaCtrl(c, dc, dataService)
    ]);
}