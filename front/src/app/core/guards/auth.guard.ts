import { inject } from "@angular/core";
import { CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { SessionService } from "../services/session.service";

export const canActivateAuth: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ) => {
    const sessionService: SessionService = inject(SessionService);
    const router = inject(Router);
    if (sessionService.isLogged() == true) {
      return true;
    }
    
    return router.createUrlTree(['/auth']);

};