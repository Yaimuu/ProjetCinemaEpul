import {CommonModule} from "@angular/common";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {FilmRoutingModule} from "./film-routing.module";
import {FilmComponent} from "./film.component";
import {FilmEditComponent} from "./film-edit/film-edit.component";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";
import {MatDialogModule} from "@angular/material/dialog";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatSelectModule} from "@angular/material/select";
import { FilmSingleComponent } from './film-single/film-single.component';

@NgModule({
  declarations: [
    FilmComponent,
    FilmEditComponent,
    FilmSingleComponent
  ],
  exports: [

  ],
  imports: [
    CommonModule,
    FilmRoutingModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatDividerModule,
    MatDialogModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule,
    MatMenuModule,
    MatExpansionModule,
    MatDatepickerModule,
    MatSelectModule
  ]
})
export class FilmModule {
}
