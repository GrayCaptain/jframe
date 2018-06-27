package org.jframe.web.demo.controllers;

import org.jframe.core.extensions.JList;
import org.jframe.data.entities.Permission;
import org.jframe.web.core.PermissionDefinition;
import org.jframe.web.demo.Menu;
import org.jframe.web.security.Authorize;
import org.jframe.web.viewModels.LayoutViewModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author qq
 * @date 2018/6/26
 */
@Controller("admin-demo-controller")
@RequestMapping("/demo")
public class DemoHomeController extends _DemoControllerBase {
    @Component
    public final class Codes implements PermissionDefinition {
        public final static String SELECT = "admin-demo-select";

        @Override
        public void registerDefinitions(JList<Permission> permissions) {
            permissions.add(new Permission("demo", "demo", "demo", SELECT));
        }

        @Override
        public void registerMenuPermissions() {
            Menu.demo().apc(SELECT);
        }
    }

    @RequestMapping
    @Authorize(permissions = {Codes.SELECT})
    public ModelAndView demo() {
        return super.tryView("demo-home-index", () -> {
            LayoutViewModel model = new LayoutViewModel();
            model.setTitle("demo index");
            return model;
        });
    }

}