package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.DTO.*;
import techsuppDev.techsupp.service.AdminProductService;
import techsuppDev.techsupp.service.FaqService;
import techsuppDev.techsupp.service.NoticeService;
import techsuppDev.techsupp.service.ProductImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminProductService adminProductService;
    private final ProductImageService productImageService;
    private final NoticeService noticeService;
    private final FaqService faqService;

    @GetMapping("/")
    public String Home() {
        return "admin/adminhome";
    }
    @GetMapping("/product/list")
    public String list(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", adminProductService.getList(pageRequestDTO));
        return "admin/Product/list";
    }

    /**
     * PRODUCT
     */

    // 상품 등록 페이지
    @GetMapping("/product/register")
    public String productRegister(Model model) {
        model.addAttribute("productForm", new ProductDTO());
        return "admin/Product/create";
    }

    // 상품 등록
    @PostMapping("/product/register")
    public String productRegisterPost(@ModelAttribute ProductDTO productDTO,
                               @RequestParam("productImgFile") List<MultipartFile> multipartFileList) throws Exception {
        adminProductService.productRegister(productDTO, multipartFileList);

        return "redirect:/admin/product/list";
    }

    @GetMapping("product/edit/{id}")
    public String productEdit(@PathVariable("id") Long id, Model model) {

        ProductDTO productDTO = adminProductService.getProductDetail(id);
        model.addAttribute("productForm", productDTO);

        return "admin/Product/edit";
    }

    @GetMapping("product/delete/{id}")
    public String removeFile(@PathVariable("id") Long id) throws IOException {

        ProductDTO productDTO = adminProductService.getProductDetail(id);

        if (productDTO.getProductImgDTOList().size() != 0) {
            Path currentPath = Paths.get("/Users/leesoyoung/Desktop/Funding/techsupp/mytechsupp/src/main/resources/static/file/product/"
                    + productDTO.getProductImgDTOList().get(0).getOriginImgName());
            Files.delete(currentPath);
            Long imgId = productDTO.getProductImgDTOList().get(0).getId();
            productImageService.deleteImageData(imgId);
        }

        adminProductService.delete(id);

        return "redirect:/admin/product/list";
    }

    // 상품 수정
    @PostMapping("product/edit/{id}")
    public String editPost(@ModelAttribute("productForm") ProductDTO productDTO,
                           @RequestParam("productImgFile") List<MultipartFile> multipartFileList) {
        try {
            adminProductService.productRegister(productDTO, multipartFileList);

        } catch (Exception e) {
            return "admin/Product/edit";
        }
        return "redirect:/admin/product/list";
    }

    @GetMapping("payment/list")
    public String paymentList(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", adminProductService.paymentList(pageRequestDTO));
        return "admin/Payment/paymentlist";
    }

    /**
     * NOTICE
     */

    // 공지사항 리스트 페이지
    @GetMapping("/notice/list")
    public String noticelist(Model model) {
        List<NoticeDTO> noticeDTOList = noticeService.findAllNotice();
        model.addAttribute("noticeList", noticeDTOList);
        return "admin/qnaService/notice";
    }

    // 공지사항 등록 페이지
    @GetMapping("/notice/register")
    public String noticeRegister(Model model) {
        model.addAttribute("notice", new NoticeDTO());
        return "admin/qnaService/notice-create";
    }

    // 공지사항 등록
    @PostMapping("/notice/register")
    public String noticeRegisterPost(@ModelAttribute NoticeDTO noticeDTO) throws Exception {
        noticeService.noticeResister(noticeDTO);
        return "redirect:/admin/notice/list";
    }

    // 공지사항 상세 및 수정
    @GetMapping("/notice/{noticeId}")
    public String noticeDetails(@PathVariable("noticeId") Long noticeId, Model model) {
        NoticeDTO noticeDTO = noticeService.findById(noticeId);
        model.addAttribute("notice", noticeDTO);
        return "admin/qnaService/notice-detail";
    }

    // 공지사항 수정
    @PostMapping("/notice/edit/{noticeId}")
    public String noticeUpdate(@ModelAttribute("notice") NoticeDTO noticeDTO) {
            try {
                noticeService.noticeUpdate(noticeDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/admin/notice/list";
    }

    // 공지사항 삭제
    @DeleteMapping ("notice/delete/{noticeId}")
    public ResponseEntity<String> noticeDelete(@PathVariable("noticeId") Long noticeId) {
        try {
            // 해당 notice 삭제
            noticeService.deleteNotice(noticeId);
            return ResponseEntity.ok().body("삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("notice 삭제 중 문제가 발생했습니다.");
        }
    }

    @DeleteMapping ("/attachedfile/delete/{noticeId}")
    public ResponseEntity<String> attachedFileDelete(@PathVariable("noticeId") Long noticeId) {
        try {
            noticeService.deleteNoticeFile(noticeId);
            return ResponseEntity.ok("첨부 파일 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("첨부 파일 삭제 실패");
        }
    }

    /**
     * FAQ
     */

    // 자주 묻는 질문 리스트 페이지
    @GetMapping("/faq/list")
    public String faqList(Model model) {
        List<FaqDTO> faqDTOList = faqService.findAll();
        model.addAttribute("faqList", faqDTOList);
        return "admin/qnaService/faq";
    }

    // 자주 묻는 질문 등록 페이지
    @GetMapping("/faq/register")
    public String faqRegister(Model model) {
        model.addAttribute("faq", new FaqDTO());
        return "admin/qnaService/faq-create";
    }

    // 자주 묻는 질문 등록
    @PostMapping("/faq/register")
    public String createFaq(@ModelAttribute FaqDTO faqDTO) throws IOException {
        // faq 작성 로직
        faqService.faqResister(faqDTO);
        System.out.println("createFaq post 확인 title : " + faqDTO.getFaqTitle());
        System.out.println("createFaq post 확인 contents : " + faqDTO.getFaqContents());
        return "redirect:/admin/faq/list";
    }

    // 자주묻는질문 상세 및 수정
    @GetMapping("/faq/{faqId}")
    public String faqDetails(@PathVariable("faqId") Long faqId, Model model) {
        FaqDTO faqDTO = faqService.findById(faqId);
        model.addAttribute("faq", faqDTO);
        return "admin/qnaService/faq-detail";
    }

    // 자주 묻는 질문 수정
    @PostMapping("/faq/edit/{faqId}")
    public String faqUpdate(@ModelAttribute("faq") FaqDTO faqDTO) {
        try {
            faqService.faqUpdate(faqDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/faq/list";
    }

    // 자주 묻는 질문 삭제
    @DeleteMapping ("faq/delete/{faqId}")
    public ResponseEntity<String> faqDelete(@PathVariable("faqId") Long faqId) {
        try {
            // 해당 faqId 삭제
            faqService.deleteFaq(faqId);
            return ResponseEntity.ok().body("삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("notice 삭제 중 문제가 발생했습니다.");
        }
    }


//    @GetMapping("payment/list")
//    public String paymentList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//        Page<AdminPaymentForm> list = adminProductService.paymentList(pageable);
//
//        int nowPage = list.getPageable().getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 9, list.getTotalPages());
//
//        model.addAttribute("list", list);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "admin/Payment/paymentlist";
//    }


}
