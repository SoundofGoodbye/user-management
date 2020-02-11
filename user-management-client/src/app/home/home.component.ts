import { Component, OnInit } from '@angular/core';
import {ColDef, ColumnApi, GridApi} from 'ag-grid-community';
import {UserService} from '../user.service';
import { User } from '../user';
import { DialogModalComponent } from '../dialog-modal/dialog-modal.component';
import {MatDialog} from "@angular/material/dialog";
import { ButtonRendererComponent } from '../button-renderer/button-renderer.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  columnDefs: ColDef[];
  rowData: User[];
  frameworkComponents: any;
  private api: GridApi;
  private columnApi: ColumnApi;

  constructor(private userService : UserService, private dialog: MatDialog) {
    this.frameworkComponents = {
      buttonRenderer: ButtonRendererComponent,
    }
    this.columnDefs = this.createColumnDefs();
   }

   onAddClick() {
        const dialogRef = this.dialog.open(DialogModalComponent,
          {
            data: {
             title: 'Create user.',
             firstName: '',
             lastName: '',
             email: '',
             action: 'Save'
           }
         });

         dialogRef.afterClosed().subscribe(user => {
           if(user != undefined)
            var res = this.api.updateRowData({ add: [user] });
         });
    }

    onUpdateClick(e) {
    const dialogRef = this.dialog.open(DialogModalComponent,
        {
          data: {
             title: 'Update user.',
             id: e.rowData.id,
             firstName: e.rowData.firstName,
             lastName: e.rowData.lastName,
             email: e.rowData.email,
             birthDate: e.rowData.birthDate,
             action: 'Update'
          }
       });

      dialogRef.afterClosed().subscribe(user => {
        var users = [];
        const selectedRow = this.api.forEachNodeAfterFilterAndSort(function(rowNode, index) {
          var data = rowNode.data;
           if(user !=undefined && user.id === rowNode.data.id) {
              rowNode.data = user;
              users.push(rowNode);
             }
        });
        
        if(users.length > 0)
          var res = this.api.updateRowData({update: [users]});
      });
    }

    onDeleteClick(e) {
      if(confirm('You are about to delete this record. Procced?')) {
          this.userService.deleteUser(e.rowData.id);
      }
    }

  getUsers() {
  this.userService.getUsers()
    .subscribe(resp => {
      for(const data of resp.body) {
        this.rowData.push(data);
      }
    });
  }

  ngOnInit() {
        this.userService.getUsers().subscribe(
            users => {
                this.rowData = users.body;
            },
            error => {
                console.log(error);
            }
        )
    }

  onGridReady(params): void {
        this.api = params.api;
        this.columnApi = params.columnApi;

        this.api.sizeColumnsToFit();
    }

    private createColumnDefs() {
        return [
              {
                 headerName: "id",
                 field: "id",
                 width: 100,
                 hide: true,
                 suppressToolPanel: true
              },
              {headerName: 'First Name', field: 'firstName', sortable:true },
              {headerName: 'Last Name', field: 'lastName', sortable: true },
              {headerName: 'Email', field: 'email'},
              {headerName: 'Birthdate', field: 'birthDate',},
              {
                headerName: 'Delete',
                cellRenderer: 'buttonRenderer',
                cellRendererParams: {
                  onClick: this.onDeleteClick.bind(this),
                  label: 'Delete'
                }
              },
              {
                headerName: 'Update',
                cellRenderer: 'buttonRenderer',
                cellRendererParams: {
                  onClick: this.onUpdateClick.bind(this),
                  label: 'Update'
                }
              }
          ];
    }
}
