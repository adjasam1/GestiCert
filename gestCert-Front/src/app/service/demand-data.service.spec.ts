import { TestBed } from '@angular/core/testing';

import { DemandDataService } from './demand-data.service';

describe('DemandDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DemandDataService = TestBed.get(DemandDataService);
    expect(service).toBeTruthy();
  });
});
