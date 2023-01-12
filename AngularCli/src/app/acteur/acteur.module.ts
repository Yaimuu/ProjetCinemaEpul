import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ActeurRoutingModule} from "./acteur-routing.module";
import {ActeurComponent} from "./acteur.component";
import {ActeurEditComponent} from "./acteur-edit/acteur-edit.component";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatCheckboxModule} from "@angular/material/checkbox";
import { ActeurSingleComponent } from './acteur-single/acteur-single.component';

@NgModule({
  declarations: [
    ActeurComponent,
    ActeurEditComponent,
    ActeurSingleComponent
  ],
  imports: [
    CommonModule,
    ActeurRoutingModule,
    MatExpansionModule,
    MatIconModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatCheckboxModule
  ]
})
export class ActeurModule { }
