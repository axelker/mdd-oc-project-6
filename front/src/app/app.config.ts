import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideAnimations } from '@angular/platform-browser/animations';

import { routes } from './app.routes';
import { provideToastr } from 'ngx-toastr';
import { provideCore } from './core/core.providers';
import { provideIcons } from '@ng-icons/core';
import { heroArrowLeft,heroArrowDown,heroArrowUp} from '@ng-icons/heroicons/outline';
import { bootstrapSend} from '@ng-icons/bootstrap-icons';

export const appConfig: ApplicationConfig = {
  providers: [
    provideToastr(),
    provideAnimations(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideCore(),
    provideIcons({ heroArrowLeft,heroArrowDown,heroArrowUp,bootstrapSend }),
    provideRouter(routes),
  ],
};
