import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementDepartmentComponent } from './management-department.component';

describe('ManagementDepartmentComponent', () => {
  let component: ManagementDepartmentComponent;
  let fixture: ComponentFixture<ManagementDepartmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementDepartmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementDepartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
