package com.mqttspringboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mqttspringboot.config.Mqtt;
import com.mqttspringboot.exception.ExceptionMessages;
import com.mqttspringboot.exception.MqttException;
import com.mqttspringboot.model.MqttPublishModel;
import com.mqttspringboot.model.MqttSubscribeModel;

@RestController
@RequestMapping(value = "/api/mqtt")
public class MqttController {

	@PostMapping("/publish")
	public Map<String, Object> publishMessage(@RequestBody @Validated MqttPublishModel messagePublishModel,
			BindingResult bindingResult) throws org.eclipse.paho.client.mqttv3.MqttException {
		if (bindingResult.hasErrors()) {
			throw new MqttException(ExceptionMessages.SOME_PARAMETERS_INVALID);
		}

		
		// List<MqttPublishModel> mqttMessage = new ArrayList<>();
	//	Mqtt.getInstanceClient().publish(messagePublishModel.getTopic(), (MqttMessage) mqttMessage);

		Map<String, Object> map = new HashMap<>();
		Map<String,Object> map1=new HashMap<>();
		map.put("qos", messagePublishModel.getQos());
		map.put("topic", messagePublishModel.getTopic());
		map.put("retained", messagePublishModel.getRetained());
		map1.put("Title", messagePublishModel.getTitle());
		map1.put("Discription", messagePublishModel.getDiscription());
		map1.put("ClickEvent", messagePublishModel.getClickEvent());
		map1.put("ImageUrl", messagePublishModel.getImageUrl());
		map1.put("IsClickable", messagePublishModel.getIsClickable());
		map1.put("Type", messagePublishModel.getType());
		
		map.put("message", map1);
		MqttMessage mqttMessage = null;
		try {
			mqttMessage = new MqttMessage(new ObjectMapper().writeValueAsString(map1).getBytes());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mqtt.getInstanceClient().publish(messagePublishModel.getTopic(), (MqttMessage) mqttMessage);

		return map;

	}

	@GetMapping("/subscribe")
	public List<MqttSubscribeModel> subscribeChannel(@RequestParam(value = "topic") String topic,
			@RequestParam(value = "wait_millis") Integer waitMillis)
			throws InterruptedException, org.eclipse.paho.client.mqttv3.MqttException {
		List<MqttSubscribeModel> messages = new ArrayList<>();
		CountDownLatch countDownLatch = new CountDownLatch(10);
		Mqtt.getInstanceClient().subscribeWithResponse(topic, (s, mqttMessage) -> {
			MqttSubscribeModel mqttSubscribeModel = new MqttSubscribeModel();
			mqttSubscribeModel.setId(mqttMessage.getId());
			mqttSubscribeModel.setMessage(new String(mqttMessage.getPayload()));
			mqttSubscribeModel.setQos(mqttMessage.getQos());
			messages.add(mqttSubscribeModel);
			countDownLatch.countDown();
		});

		countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);

		return messages;
	}

}
