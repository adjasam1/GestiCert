import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {DepartmentDataService} from '../../../service/department-data.service';
import {Department} from '../../../model/department';
import {Profile} from '../../../model/profile';
import {NgForm} from '@angular/forms';

@Component ({
  selector: 'app-management-department',
  templateUrl: './management-department.component.html',
  styleUrls: ['./management-department.component.scss']
})
export class ManagementDepartmentComponent implements OnInit {

  departmentsList: BehaviorSubject<Department[]>;
  idDepartment: number;
  editedDepartment: Department = new Department(0, '');

  departments: Department;
  cols: any;
  selectedDepartment: Department;

  constructor(private departmentDataService: DepartmentDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.departmentsList = this.departmentDataService.availableDepartments$;
    this.idDepartment = +this.route.snapshot.params.id;
    this.departmentDataService.findDepartment(this.idDepartment).subscribe(department => {
      this.editedDepartment = department;
    });

    this.onRefresh();

    this.cols = [
      { field: 'nameDepartment', header: 'Nom' }
    ];
  }

  onRefresh() {
    this.departmentDataService.getDepartmentPrimeNg().then(departments => this.departments = departments);
  }

  onSave(logForm: NgForm) {
    if (!this.idDepartment) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouveau service ?')) {
        this.departmentDataService.createDepartment(this.editedDepartment).subscribe( createDepartment => {
          this.onRefresh();
          logForm.reset();
          this.router.navigate(['/gestion/pro']);
          this.onRefresh();
        });
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier ce service ?')) {
        this.departmentDataService.updateDepartment(this.editedDepartment).subscribe( updateDepartment => {
          this.onRefresh();
          this.router.navigate(['/gestion/pro']);
          this.onRefresh();
        });
      }
    }
    this.router.navigate(['/gestion/sce']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer ce service ?')) {
      this.departmentDataService.deleteDepartment(this.editedDepartment).subscribe( deleteDepartment => {
        this.onRefresh();
      });
    }
    this.router.navigate(['/gestion/sce']);
  }

}




