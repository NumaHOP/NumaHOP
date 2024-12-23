
package fr.progilone.pgcn.service.exchange.iiif.manifest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "@type", "@id", "@container" })
public class Item {

	@JsonProperty("@type")
	private String type;

	@JsonProperty("@id")
	private String id;

	@JsonProperty("@container")
	private String container;

	@JsonIgnore
	@Valid
	private Map<String, Object> additionalProperties = new HashMap<>();

	/**
	 * No args constructor for use in serialization
	 */
	public Item() {
	}

	/**
	 * @param id
	 * @param container
	 * @param type
	 */
	public Item(String type, String id, String container) {
		super();
		this.type = type;
		this.id = id;
		this.container = container;
	}

	@JsonProperty("@type")
	public String getType() {
		return type;
	}

	@JsonProperty("@type")
	public void setType(String type) {
		this.type = type;
	}

	public Item withType(String type) {
		this.type = type;
		return this;
	}

	@JsonProperty("@id")
	public String getId() {
		return id;
	}

	@JsonProperty("@id")
	public void setId(String id) {
		this.id = id;
	}

	public Item withId(String id) {
		this.id = id;
		return this;
	}

	@JsonProperty("@container")
	public String getContainer() {
		return container;
	}

	@JsonProperty("@container")
	public void setContainer(String container) {
		this.container = container;
	}

	public Item withContainer(String container) {
		this.container = container;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Item withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
