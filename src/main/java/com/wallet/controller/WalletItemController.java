package com.wallet.controller;

import com.wallet.dto.WalletItemDTO;
import com.wallet.entity.WalletItem;
import com.wallet.service.UserWalletService;
import com.wallet.service.WalletItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("wallet-item")
@RestController
public class WalletItemController {

	@Autowired
	private WalletItemService service;

	@Autowired
	private UserWalletService userWalletService;

    @Autowired
    private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<WalletItemDTO> create (@Valid @RequestBody WalletItemDTO dto) {
        var walletItem = service.save(modelMapper.map(dto, WalletItem.class));



		return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(walletItem, WalletItemDTO.class));
	}

//	@GetMapping("/{walletId}")
//	public ResponseEntity<Response<Page<WalletItemDTO>>> findBetweenDates (@PathVariable("walletId") Long walletId,
//			@RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
//			@RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
//			@RequestParam(name = "page", defaultValue = "0") int page) {
//
//		Response<Page<WalletItemDTO>> response = new Response<Page<WalletItemDTO>>();
//
//		Optional<UserWallet> uw = userWalletService.findByUsersIdAndWalletId(Util.getAuthenticatedUserId(), walletId);
//
//		if (!uw.isPresent()) {
//			response.getErrors().add("Voce nao tem acesso a essa carteira.");
//			return ResponseEntity.badRequest().body(response);
//		}
//
//		Page<WalletItem> items = service.findBetweenDates(walletId, startDate, endDate, page);
//		Page<WalletItemDTO> dto = items.map(i -> this.convertEntityToDto(i));
//		response.setData(dto);
//
//
//		return ResponseEntity.ok().body(response);
//	}
//
//	@GetMapping("/type/{wallet}")
//	public ResponseEntity<Response<List<WalletItemDTO>>> findByWalletAndType (@PathVariable("wallet") Long wallet,
//			@RequestParam("type") String type) {
//
//		Response<List<WalletItemDTO>> response = new Response<List<WalletItemDTO>>();
//		List<WalletItem> list = service.findByWalletAndType(wallet, TypeEnum.getEnum(type));
//
//		List<WalletItemDTO> dto = new ArrayList<>();
//		list.forEach(i -> dto.add(this.convertEntityToDto(i)));
//		response.setData(dto);
//
//		return ResponseEntity.ok().body(response);
//	}
//
//	@GetMapping("/total/{wallet}")
//	public ResponseEntity<Response<BigDecimal>> sumByWalletId(@PathVariable("wallet") Long wallet) {
//		Response<BigDecimal> response = new Response<BigDecimal>();
//
//		BigDecimal value = service.sumByWalletId(wallet);
//		response.setData(value == null ? BigDecimal.ZERO : value);
//
//		return ResponseEntity.ok().body(response);
//	}
//
//	@PutMapping
//	public ResponseEntity<Response<WalletItemDTO>> update(@Valid @RequestBody WalletItemDTO dto, BindingResult result) {
//
//		Response<WalletItemDTO> response = new Response<WalletItemDTO>();
//
//		Optional<WalletItem> wi = service.findById(dto.getId());
//
//		if (!wi.isPresent()) {
//			result.addError(new ObjectError("WalletItem", "WalletItem nao encontrado."));
//		} else if (wi.get().getWallet().getId().compareTo(dto.getWallet()) != 0) {
//			result.addError(new ObjectError("WalletItemChanged", "Voce nao pode alterar esta carteira."));
//		}
//
//		if (result.hasErrors()) {
//			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
//
//			return ResponseEntity.badRequest().body(response);
//		}
//
//		WalletItem saved = service.save(this.convertDtoToEntity(dto));
//
//		response.setData(this.convertEntityToDto(saved));
//
//		return ResponseEntity.ok().body(response);
//
//	}
//
//
//	@DeleteMapping("/{walletId}")
//	public ResponseEntity<Response<String>> delete (@PathVariable("walletId") Long walletId) {
//		Response<String> response = new Response<String>();
//
//		Optional<WalletItem> wi = service.findById(walletId);
//
//		if (!wi.isPresent()) {
//			response.getErrors().add("Wallet Item de ID " + walletId + " nao encontrado.");
//
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//		}
//
//		service.deleteById(walletId);
//		response.setData("Wallet Item de id " + walletId + " apagada com sucesso. ");
//
//
//		return ResponseEntity.ok().body(response);
//	}
//
//	public WalletItem convertDtoToEntity (WalletItemDTO dto) {
//		WalletItem wi = new WalletItem();
//		wi.setDate(dto.getDate());
//		wi.setDescription(dto.getDescription());
//		wi.setId(dto.getId());
//		wi.setType(TypeEnum.getEnum(dto.getType()));
//		wi.setValue(dto.getValue());
//
//
//		Wallet w = new Wallet();
//		w.setId(dto.getWallet());
//		wi.setWallet(w);
//
//		return wi;
//	}
//
//	public WalletItemDTO convertEntityToDto (WalletItem wi) {
//		WalletItemDTO dto = new WalletItemDTO();
//		dto.setDate(wi.getDate());
//		dto.setDescription(wi.getDescription());
//		dto.setId(wi.getId());
//		dto.setType(wi.getType().getValue());
//		dto.setValue(wi.getValue());
//		dto.setWallet(wi.getWallet().getId());
//
//		return dto;
}

