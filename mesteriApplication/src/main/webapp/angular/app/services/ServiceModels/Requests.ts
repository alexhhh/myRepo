module App.Services {
 
    export class GetSpecialityRequest {
    }
    
    export class AddSpecialityRequest {
        public specialityName: string;
    }   
      export class DeleteSpecialityRequest {
        public idSpeciality: string;
    }   
}