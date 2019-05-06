import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementEnvironmentComponent } from './management-environment.component';

describe('ManagementEnvironmentComponent', () => {
  let component: ManagementEnvironmentComponent;
  let fixture: ComponentFixture<ManagementEnvironmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementEnvironmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementEnvironmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
