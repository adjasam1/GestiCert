import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementApplicationComponent } from './management-application.component';

describe('ManagementApplicationComponent', () => {
  let component: ManagementApplicationComponent;
  let fixture: ComponentFixture<ManagementApplicationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementApplicationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
