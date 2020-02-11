import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AgGridModule } from 'ag-grid-angular';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {UserService} from './user.service';

// Material imports
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DialogModalComponent } from './dialog-modal/dialog-modal.component';
import { ButtonRendererComponent } from './button-renderer/button-renderer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DialogModalComponent,
    ButtonRendererComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    AgGridModule.withComponents([ButtonRendererComponent]),
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule
  ],
  entryComponents: [
    DialogModalComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
