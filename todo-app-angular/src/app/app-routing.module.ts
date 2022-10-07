import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { CreateComponent } from './create/create.component';
import { ListComponent } from './list/list.component';


const routes: Routes = [
  { path: '', redirectTo: 'index', pathMatch: 'full'},
  { path: 'index', component: IndexComponent },
  {path: 'create', component: CreateComponent},
  {path:'mytodos', component: ListComponent},
  {path: 'edit/:id', component: CreateComponent, pathMatch: "prefix" }
  
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
