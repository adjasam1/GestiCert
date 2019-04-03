import { TestBed } from '@angular/core/testing';

import { DepartmentDataService } from './department-data.service';

describe('DepartmentDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DepartmentDataService = TestBed.get(DepartmentDataService);
    expect(service).toBeTruthy();
  });
});
