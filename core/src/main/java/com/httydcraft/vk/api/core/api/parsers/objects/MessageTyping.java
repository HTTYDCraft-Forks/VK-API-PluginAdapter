package com.httydcraft.vk.api.core.api.parsers.objects;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Represents a typing event in VK messages.
 */
public class MessageTyping {
    // #region Fields
    @SerializedName("state")
    private String state;

    @SerializedName("from_id")
    private Integer fromId;

    @SerializedName("to_id")
    private Integer toId;
    // #endregion

    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    // #region Getters and Setters
    /**
     * Gets the typing state.
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the typing state.
     * @param state typing state
     * @return this
     */
    public MessageTyping setState(String state) {
        Preconditions.checkNotNull(state, "state cannot be null");
        this.state = state;
        logger.atFine().log("Set state: %s", state);
        return this;
    }

    /**
     * Gets the sender's id.
     * @return fromId
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     * Sets the sender's id.
     * @param fromId sender id
     * @return this
     */
    public MessageTyping setFromId(Integer fromId) {
        Preconditions.checkNotNull(fromId, "fromId cannot be null");
        this.fromId = fromId;
        logger.atFine().log("Set fromId: %d", fromId);
        return this;
    }

    /**
     * Gets the recipient's id.
     * @return toId
     */
    public Integer getToId() {
        return toId;
    }

    /**
     * Sets the recipient's id.
     * @param toId recipient id
     * @return this
     */
    public MessageTyping setToId(Integer toId) {
        Preconditions.checkNotNull(toId, "toId cannot be null");
        this.toId = toId;
        logger.atFine().log("Set toId: %d", toId);
        return this;
    }
    // #endregion

    // #region Object overrides
    @Override
    public int hashCode() {
        return Objects.hash(state, fromId, toId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MessageTyping messageTyping = (MessageTyping) o;
        return java.util.Objects.equals(state, messageTyping.state)
                && java.util.Objects.equals(fromId, messageTyping.fromId)
                && java.util.Objects.equals(toId, messageTyping.toId);
    }

    @Override
    public String toString() {
        final com.google.gson.Gson gson = new com.google.gson.Gson();
        return gson.toJson(this);
    }

    /**
     * Returns a pretty string representation.
     * @return pretty string
     */
    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("MessageTyping{");
        sb.append("state='").append(state).append("'");
        sb.append(", fromId=").append(fromId);
        sb.append(", toId=").append(toId);
        sb.append('}');
        return sb.toString();
    }
    // #endregion
}
