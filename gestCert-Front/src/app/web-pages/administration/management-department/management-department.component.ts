import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {DepartmentDataService} from '../../../service/department-data.service';
import {Department} from '../../../model/department';

@Component ({
  selector: 'app-management-department',
  templateUrl: './management-department.component.html',
  styleUrls: ['./management-department.component.scss']
})
export class ManagementDepartmentComponent implements OnInit {

  departmentsList: BehaviorSubject<Department[]>;
  idDepartment: number;
  editedDepartment: Department = new Department(0, '');

  constructor(private departmentDataService: DepartmentDataService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.departmentDataService.publishDepartment();

    this.departmentsList = this.departmentDataService.availableDepartments$;
    this.idDepartment = +this.route.snapshot.params.id;
    this.departmentDataService.findDepartment(this.idDepartment).subscribe(department => { this.editedDepartment = department; });
  }

  onSave() {
    if (!this.idDepartment) {
      if (confirm('Êtes-vous certain de vouloir ajouter un nouvel service ?')) {
        this.departmentDataService.createDepartment(this.editedDepartment);
      }
    } else {
      if (confirm('Êtes-vous certain de vouloir modifier ce service ?')) {
        this.departmentDataService.updateDepartment(this.editedDepartment);
      }
    }
    this.router.navigate(['/gestion/sce']);
  }

  onDelete() {
    if (confirm('Êtes-vous certain de vouloir supprimer ce service ?')) {
      this.departmentDataService.deleteDepartment(this.editedDepartment);
    }
    this.router.navigate(['/gestion/sce']);
  }

}




