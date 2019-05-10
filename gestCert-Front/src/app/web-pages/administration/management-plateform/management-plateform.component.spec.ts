import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementPlateformComponent } from './management-plateform.component';

describe('ManagementPlateformComponent', () => {
  let component: ManagementPlateformComponent;
  let fixture: ComponentFixture<ManagementPlateformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementPlateformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementPlateformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
