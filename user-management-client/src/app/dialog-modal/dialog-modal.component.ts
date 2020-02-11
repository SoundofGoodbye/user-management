import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import {FormBuilder, Validators, FormGroup} from "@angular/forms";
import * as moment from 'moment';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import { User } from '../user';
import {UserService} from '../user.service';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';

@Component({
  selector: 'app-dialog-modal',
  templateUrl: './dialog-modal.component.html',
  styleUrls: ['./dialog-modal.component.css']
})
export class DialogModalComponent implements OnInit {

  form: FormGroup;
  description:string;
  user: User;
  userObs: Observable<User>;
  date: string;

  constructor(private fb: FormBuilder,
     private dialogRef: MatDialogRef<DialogModalComponent>,
     private userService: UserService,
     @Inject(MAT_DIALOG_DATA) public data: {
                     title: string,
                     id: number,
                     firstName: string,
                     lastName:string,
                     email:string,
                     birthDate:string,
                     action:string
                 })
   {
     this.form = fb.group({
            id:[data.id],
            firstName: [data.firstName, Validators.required],
            lastName: [data.lastName, Validators.required],
            email: [data.email, Validators.required],
            action:[data.action],
            birthDate:[data.birthDate, Validators.required]
        });
   }

  ngOnInit() {}

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.form.value.birthDate = moment(new Date(event.value)).format("YYYY-MM-DD");
  }

  public save(): Observable<User> {
    if(this.data.action.toLowerCase() === 'update') {
      this.userObs = this.updateData();
    } else if(this.data.action.toLowerCase() === 'save') {
      this.userObs = this.saveData();
    }

    this.dialogRef.close(this.form.value);
    console.log("User obs");
    console.log(this.userObs);

    return this.userObs;
   }

   close() {
       this.dialogRef.close();
   }

   saveData(): Observable<User> {
     this.user = {
       id: 0,
       firstName: this.form.value.firstName,
       lastName: this.form.value.lastName,
       email: this.form.value.email,
       birthDate: this.form.value.birthDate
     }
     return this.userService.addUser(this.user).pipe(map(resp => {
       return resp;
     }));
   }

   updateData(): Observable<User> {
     this.user = {
       id: this.data.id,
       firstName: this.form.value.firstName,
       lastName: this.form.value.lastName,
       email: this.form.value.email,
       birthDate: this.form.value.birthDate
     };

     return this.userService.updateUser(this.user);
   }

}
