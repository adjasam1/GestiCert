import { TestBed } from '@angular/core/testing';

import { PlateformDataService } from './plateform-data.service';

describe('PlateformDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PlateformDataService = TestBed.get(PlateformDataService);
    expect(service).toBeTruthy();
  });
});
