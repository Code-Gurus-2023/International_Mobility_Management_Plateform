import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TemplateComponent } from './template/template.component';
import { ForumPageComponent } from './forum/forum-page/forum-page.component';

const routes: Routes = [
  {
    path:'',
    component:TemplateComponent,
    children:[
     {path:'forumList',component:ForumPageComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackendRoutingModule { }
