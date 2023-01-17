package shop.mtcoding.orange.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.orange.model.Product;
import shop.mtcoding.orange.model.ProductRepository;

@Controller
public class ProductController {

    @Autowired // Type으로 찾아냄
    private ProductRepository productRepository;

    @Autowired
    private HttpSession session;

    // dispatcher servlet, 톰캣에서 받아온 것.
    // 의존성 주입
    @GetMapping({ "/", "/product" }) // uri 두 가지 다 사용
    public String findAll(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/main"; // request 새로 만들어짐 -> 덮어 씌움 (프레임워크가 해주는 마법)
    }

    @GetMapping("/product/{id}")
    public String findOne(@PathVariable int id, Model model) {
        Product product = productRepository.findOne(id); // mybatis가 매핑
        model.addAttribute("product", product);
        return "product/detail"; // request 새로 만들어짐 -> 덮어 씌움 (프레임워크가 해주는 마법)
    }

    @GetMapping("/product/addForm")
    public String addForm() {
        return "product/addForm";
    }

    @PostMapping("/product/add")
    public String add(String name, int price, int qty) { // int라고만 적어주면 string에서 int로 파싱해준다
        int result = productRepository.insert(name, price, qty);
        if (result == 1) {
            return "redirect:/product";
        } else {
            return "redirect:/product/addForm";
        }
    }

    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable int id) {
        int result = productRepository.delete(id);
        if (result == 1) {
            return "redirect:/";
        } else {
            return "redirect:/product" + id;
        }
    }

    @GetMapping("/product/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        Product product = productRepository.findOne(id);
        model.addAttribute("product", product);
        return "product/updateForm";
    }

    @PostMapping("/product/{id}/update")
    public String update(@PathVariable int id, String name, int price, int qty) {
        int result = productRepository.update(id, name, price, qty);
        if (result == 1) {
            return "redirect:/product/" + id;
        } else {
            return "redirect:/product/" + id + "/updateForm";
        }
    }
}
