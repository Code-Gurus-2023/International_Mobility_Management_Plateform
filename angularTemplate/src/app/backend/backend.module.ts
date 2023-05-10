import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackendRoutingModule } from './backend-routing.module';
import { TemplateComponent } from './template/template.component';
import { ForumService } from './services/forum.service';
import { ForumTableComponent } from './forum/forum-table/forum-table.component';
import { ForumPageComponent } from './forum/forum-page/forum-page.component';
import { PaginationModule } from '../pagination/pagination.module';



@NgModule({
    declarations: [
        TemplateComponent,
        ForumTableComponent,
        ForumPageComponent
    ],
    providers: [
        ForumService
    ],
    imports: [
        CommonModule,
        BackendRoutingModule,
        PaginationModule
    ]
})
export class BackendModule { }
