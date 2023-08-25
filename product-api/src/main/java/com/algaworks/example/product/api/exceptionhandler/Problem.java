package com.algaworks.example.product.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Problem {
	private Integer status;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private OffsetDateTime timestamp;
	private List<Object> objects;

	Problem(final Integer status, final String type, final String title, final String detail, final String userMessage, final OffsetDateTime timestamp, final List<Object> objects) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.detail = detail;
		this.userMessage = userMessage;
		this.timestamp = timestamp;
		this.objects = objects;
	}

	public static ProblemBuilder builder() {
		return new ProblemBuilder();
	}

	public Integer getStatus() {
		return this.status;
	}

	public String getType() {
		return this.type;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDetail() {
		return this.detail;
	}

	public String getUserMessage() {
		return this.userMessage;
	}

	public OffsetDateTime getTimestamp() {
		return this.timestamp;
	}

	public List<Object> getObjects() {
		return this.objects;
	}

	public static class ProblemBuilder {
		private Integer status;
		private String type;
		private String title;
		private String detail;
		private String userMessage;
		private OffsetDateTime timestamp;
		private List<Object> objects;

		ProblemBuilder() {
		}

		public ProblemBuilder status(final Integer status) {
			this.status = status;
			return this;
		}

		public ProblemBuilder type(final String type) {
			this.type = type;
			return this;
		}

		public ProblemBuilder title(final String title) {
			this.title = title;
			return this;
		}

		public ProblemBuilder detail(final String detail) {
			this.detail = detail;
			return this;
		}

		public ProblemBuilder userMessage(final String userMessage) {
			this.userMessage = userMessage;
			return this;
		}

		public ProblemBuilder timestamp(final OffsetDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ProblemBuilder objects(final List<Object> objects) {
			this.objects = objects;
			return this;
		}

		public Problem build() {
			return new Problem(this.status, this.type, this.title, this.detail, this.userMessage, this.timestamp, this.objects);
		}

		public String toString() {
			return "Problem.ProblemBuilder(status=" + this.status + ", type=" + this.type + ", title=" + this.title + ", detail=" + this.detail + ", userMessage=" + this.userMessage + ", timestamp=" + this.timestamp + ", objects=" + this.objects + ")";
		}
	}

	public static class Object {
		private String name;
		private String userMessage;

		Object(final String name, final String userMessage) {
			this.name = name;
			this.userMessage = userMessage;
		}

		public static ObjectBuilder builder() {
			return new ObjectBuilder();
		}

		public String getName() {
			return this.name;
		}

		public String getUserMessage() {
			return this.userMessage;
		}

		public static class ObjectBuilder {
			private String name;
			private String userMessage;

			ObjectBuilder() {
			}

			public ObjectBuilder name(final String name) {
				this.name = name;
				return this;
			}

			public ObjectBuilder userMessage(final String userMessage) {
				this.userMessage = userMessage;
				return this;
			}

			public Object build() {
				return new Object(this.name, this.userMessage);
			}

			public String toString() {
				return "Problem.Object.ObjectBuilder(name=" + this.name + ", userMessage=" + this.userMessage + ")";
			}
		}
	}
}
